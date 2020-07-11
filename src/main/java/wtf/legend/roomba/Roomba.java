package wtf.legend.roomba;

import wtf.legend.roomba.discord.DiscordClient;

import javax.security.auth.login.LoginException;
import java.math.RoundingMode;

public class Roomba {

    private DiscordClient discordClient = null;

    public static void main(String... args) {
        Roomba roomba = new Roomba();
        roomba.init(args);
    }

    public void init(String... args) {
        try {
            discordClient = new DiscordClient(args[0]);
        } catch (LoginException ex) {
            ex.printStackTrace();
        }
    }

}
