package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class HelpCommand implements DiscordCommand {
    
    public String getCommand() {
        return null;
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {

    }

    public int requiredArgs() {
        return 0;
    }
}
