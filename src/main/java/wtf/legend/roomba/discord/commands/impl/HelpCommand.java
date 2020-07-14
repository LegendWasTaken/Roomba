package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.ErrorType;
import wtf.legend.roomba.discord.Lang;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class HelpCommand implements DiscordCommand {

    private DiscordCommand[] commands;

    public void setCommands(DiscordCommand[] commands) {
        this.commands = commands;
    }

    public String getCommand() {
        return "help";
    }

    public String getUsage() {
        return "help [command]";
    }

    public String getDescription() {
        return "Why are you asking for help on the help command? Is it not obvious what this command does? Why are you doing this, in fact whoever did this. Now there's going to be chat history showing that you for some reason like to check things that should be obvious. Smh";
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {
        if (args.length == 0) {
            channel.sendMessage(Lang.getHelpEmbed(this.commands)).queue();
        } else {
            DiscordCommand target = null;
            for (int i=0; i<this.commands.length; i++) {
                String[] aliases = this.commands[i].getAliases();
                for (int a = 0; a < aliases.length; a++) {
                    if (aliases[a].equals(args[0]) || this.commands[i].getCommand().equals(args[0])) {
                        target = this.commands[i];
                        break;
                    }
                }
            }
            if (target != null) {
                channel.sendMessage(Lang.getCommandInformationEmbed(target)).queue();
            } else {
                channel.sendMessage(Lang.getErrorEmbed(ErrorType.UNKNOWN_COMMAND)).queue();
            }
        }
    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {"iamconfusion", "wtfrick"};
    }
}
