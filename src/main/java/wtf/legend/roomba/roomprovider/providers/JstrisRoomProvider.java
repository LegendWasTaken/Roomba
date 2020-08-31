package wtf.legend.roomba.roomprovider.providers;

import net.dv8tion.jda.api.entities.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wtf.legend.roomba.roomprovider.IServiceRoomProvider;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JstrisRoomProvider implements IServiceRoomProvider {

    public String createRoom() {
        return createRoomWithSettings(2);
    }

    private String createRoomWithSettings(int playerCount) {
        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
        WebDriver driver = new FirefoxDriver(options);

        /* Actually create the Jstris room */
        driver.get("https://jstris.jezevec10.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lobby")));
        driver.findElement(By.id("lobby")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createRoomButton")));
        driver.findElement(By.id("createRoomButton")).click();
        driver.findElement(By.id("roomName")).clear();
        driver.findElement(By.id("roomName")).sendKeys("MMC | Roomba");
        driver.findElement(By.id("isPrivate")).click();
        // Script Stuff

        ((JavascriptExecutor)driver).executeScript("document.getElementById(\"numPlayers\").selectedIndex = 0");

        try {
            Thread.sleep(250);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        driver.findElement(By.id("create")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("joinLink")));
        String url = driver.findElements(By.className("joinLink")).get(0).getAttribute("innerText");

        Thread waitForUserThread = new Thread(() -> {
            try {
                long startTimeCheck = System.currentTimeMillis();
                while (true) {
                    Thread.sleep(100);
                    if (startTimeCheck + 30000 < System.currentTimeMillis()) break; // The user has taken too long (30 seconds)
                    Object msgCount = ((JavascriptExecutor)driver).executeScript("return document.getElementsByClassName(\"chl srv\").length;");
                    if (msgCount instanceof Long) {
                        long amount = (long) msgCount;
                        if (amount > 2) {
                            driver.findElement(By.id("chatInput")).sendKeys("[MMC] User has joined room, leaving.");
                            driver.findElement(By.id("sendMsg")).click();
                            driver.close();
                            break;
                        }
                    } else {
                        break; // How the hell did we manage to get to this lmao
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                driver.close();
            }
        });
        waitForUserThread.start();
        return url;
    }

    public String getProviderName() {
        return "jstris";
    }

    @Override
    public Set<String> getProviderAliases() {
        return new HashSet<>(Arrays.asList("jstris", "j", "jst"));
    }
}
