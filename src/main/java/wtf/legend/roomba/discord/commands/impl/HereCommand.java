package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class HereCommand implements DiscordCommand {

    public String getCommand() {
        return "here";
    }

    public String getUsage() {
        return "here [game]";
    }

    public String getDescription() {
        return "Mentions all online users (in APM matchmaking channels only) to quickly find someone to play with. Has a cooldown of 30 minutes per channel.";
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {

    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {};
    }
}
