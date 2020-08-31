package wtf.legend.roomba;

import net.dv8tion.jda.api.entities.Message;
import wtf.legend.roomba.discord.DiscordClient;
import wtf.legend.roomba.discord.ErrorType;
import wtf.legend.roomba.discord.Lang;
import wtf.legend.roomba.exceptions.NoRoomProviderFoundException;
import wtf.legend.roomba.roomprovider.IServiceRoomProvider;
import wtf.legend.roomba.roomprovider.providers.JstrisRoomProvider;

import javax.security.auth.login.LoginException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
    private List<IServiceRoomProvider> IServiceRoomProviders = null;

    public static Roomba instance;
    public static Roomba getInstance() {
        return instance;
    }

    public Roomba() {
        this.IServiceRoomProviders = new ArrayList<IServiceRoomProvider>();
    }

    public void init(String... args) {
        instance = this;
        // Register the discord client
        try {
            discordClient = new DiscordClient(args[0]);
        } catch (LoginException ex) {
            ex.printStackTrace();
        }

        // Register the room providers
        this.IServiceRoomProviders.add(new JstrisRoomProvider());

    }

    public static void createRoom(Message discordMessage, String replace, String roomProviderId) {
        try {
            Thread thread = new Thread(() -> {
                try {
                    IServiceRoomProvider provider = getRoomProvider(roomProviderId);
                    if (provider == null) {
                        discordMessage.editMessage(Lang.getErrorEmbed(ErrorType.UNKNOWN_ROOM_PROVIDER)).queue();
                    } else {
                        String roomLink = provider.createRoom();
                        discordMessage.editMessage(Lang.getCreatedRoomEmbed(roomLink)).queue();
                    }
                } catch (Exception ex) {
                    discordMessage.editMessage(Lang.getErrorEmbed(ErrorType.ROOM_CREATION)).queue();
                    ex.printStackTrace();
                }
            });
            thread.start();
        } catch (Exception ex) {
            discordMessage.editMessage(Lang.getErrorEmbed(ErrorType.ROOM_CREATION)).queue();
        }
    }

    public static IServiceRoomProvider getRoomProvider(String name) throws NoRoomProviderFoundException {
        for (int i = 0; i < instance.IServiceRoomProviders.size(); i++) {
            if (instance.IServiceRoomProviders.get(i).getProviderAliases().contains(name))
                return instance.IServiceRoomProviders.get(i);
        }
        return null;
    }
}
