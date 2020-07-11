package wtf.legend.roomba.discord.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

public interface DiscordCommand {
    String getCommand();
    void execute(MessageChannel channel, Member author, String command, String... args);
    int requiredArgs(); // Todo: Something with this
}
