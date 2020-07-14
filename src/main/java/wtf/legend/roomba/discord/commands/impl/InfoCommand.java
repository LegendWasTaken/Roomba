package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.Lang;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class InfoCommand implements DiscordCommand {

    public String getCommand() {
        return "info";
    }

    public String getUsage() {
        return "N/A";
    }

    public String getDescription() {
        return "Gives you information about the bot!";
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {
        channel.sendMessage(Lang.getInformationEmbed()).queue();
    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {"botinfo", "whatisthisbot", "information"};
    }
}
