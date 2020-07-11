package wtf.legend.roomba;

import wtf.legend.roomba.discord.DiscordClient;

import javax.security.auth.login.LoginException;

public class Roomba {

    /**
     * Things Roomba still needs till "done" and a release package is made and released
     * (Done in the order of which I'm going to get them done, importance is stated in brackets)
     * Format:
     * To/do: {Thing that needs to be done} {How much it matters 0-10}
     * Todo: Discord Commands {9}
     * Todo: Configuration and language files {7}
     * Todo: Figure out illegal command argument handling in a nice way {3}
     * Todo: Room creator service interface {8}
     * Todo: Create a room creator service for Jstris / WWC / Tetrio {7}
     * Todo: Write documentation for room creator services so people can help and add to them {2}
     */

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
