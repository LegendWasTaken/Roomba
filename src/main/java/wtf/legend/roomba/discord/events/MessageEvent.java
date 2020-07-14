package wtf.legend.roomba.discord.events;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wtf.legend.roomba.discord.DiscordClient;
import wtf.legend.roomba.discord.Lang;

import java.util.Properties;

public class MessageEvent extends ListenerAdapter {

    private DiscordClient client;
    public MessageEvent(DiscordClient client) {
        this.client = client;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().getId().equals("597923739249934347")) {
            // whY dO yOu UsE A BoOlEAn InsTeAd Of pUtTiNg It DirEctlY iN thE iF sTatEmenT?
            // Simple answer, because it makes the code easier to read for people who don't know much code
            // And makes it easier on the eyes for people who want to read the code and learn from it.
            boolean validUser = !event.getAuthor().isBot();
            boolean startsWithPrefix = event.getMessage().getContentStripped().startsWith(Lang.PREFIX);
            if(validUser && startsWithPrefix) {
                MessageChannel channel = event.getChannel();
                Member author = event.getMember();

                // Yes I know I can do this better, I'm not going to because I'm lazy.
                // If you want to complain about it, make a pull request and I'll review it.
                String[] messages = event.getMessage().getContentStripped().replaceAll(Lang.PREFIX, "").split("\\s+");

                // For the love of god and all things holy, I can't think of a nice way to implement this correctly
                if(messages.length == 0) {
                    // This means someone decided to be funny and run "{@prefix}" with nothing else,
                    // Now I could go ahead and make a nice way to implement some type of message that would allow people to customize it and change it on their own
                    // But that's a lot of effort so instead I'm just going to do silent "error" catching.
                    // Very very good software design I know.
                } else {
                    String command = messages[0];
                    String[] args = new String[messages.length - 1];
                    // Now I have no clue if this can cause an error, and given what this is doing there is a 500% chance that I'm doing something wrong.
                    // Once again, refer to the. "Don't complain unless you suggest a fix that's decent"
                    for (int i = 0; i < args.length; i++) args[i] = messages[i + 1];
                    this.client.commandEvent(channel, author, command, args);
                }
            }
        }
    }

}
