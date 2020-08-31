package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import wtf.legend.roomba.Roomba;
import wtf.legend.roomba.discord.Lang;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class CreateRoomCommand implements DiscordCommand {

    public String getCommand() {
        return "createroom";
    }

    public String getUsage() {
        return "createroom <website> [players] [preset]";
    }

    public String getDescription() {
        return "Use this command to create a Tetris room!";
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {
        Message message = channel.sendMessage(Lang.getCreatingRoomEmbed()).complete();
//        channel.sendMessage(Lang.getCreatingRoomEmbed()).queue();
        Roomba.createRoom(message, "e", "jstris");
    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {"cr", "makeroom", "blessmewithatetrisroom"};
    }
}
