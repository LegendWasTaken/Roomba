package wtf.legend.roomba;

import java.net.URISyntaxException;

class Main {
    public static void main(String... args) throws URISyntaxException {
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        System.setProperty("webdriver.gecko.driver", args[1]);
        Roomba roomba = new Roomba();
        roomba.init(args);
    }
}
