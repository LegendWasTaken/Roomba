package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class PingCommand implements DiscordCommand {

    public String getCommand() {
        return "ping";
    }

    public String getUsage() {
        return "N/A";
    }

    public String getDescription() {
        return "Pong!";
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {
        channel.sendMessage("Pong").queue();

    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {"test"};
    }
}
