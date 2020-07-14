package wtf.legend.roomba.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import wtf.legend.roomba.discord.commands.DiscordCommand;

import java.awt.*;
import java.util.Arrays;

public class Lang {

    public static String PREFIX = "=";

    /* Error message */
    public static String NOT_ENOUGH_ARGS_ERROR = "You must specify more arguments!";
    public static String UNKNOWN_COMMAND_ERROR = "This command does not exist!";
    public static String UNKNOWN_ERROR_ERROR = "An unknown error has occurred!";

    /* Help Embed */
    public static String HELP_EMBED_TITLE = "**Roomba Help**";
    public static String HELP_EMBED_COMMAND_ENTRY_FORMAT = "`{PREFIX}{NAME}` **>** `{PREFIX}{USAGE}`\n";
    public static String HELP_EMBED_FOOTER = "Roomba | Made by Legend#4321";
    public static int HELP_EMBED_R = 100;
    public static int HELP_EMBED_G = 100;
    public static int HELP_EMBED_B = 200;

    /* Command Specific Embed */
    public static String COMMAND_HELP_EMBED_TITLE = "**Roomba Help**";
    public static String COMMAND_HELP_EMBED_COMMAND_FORMAT = "**Usage**: {USAGE}\n**Aliases:** {ALIASES}\n**Description:** {DESCRIPTION}";
    public static String COMMAND_HELP_EMBED_FOOTER = "Roomba | Made by Legend#4321";
    public static int COMMAND_HELP_EMBED_R = 100;
    public static int COMMAND_HELP_EMBED_G = 100;
    public static int COMMAND_HELP_EMBED_B = 200;

    /* Error Embeds */
    public static String ERROR_EMBED_TITLE = "Error";
    public static String ERROR_EMBED_FOOTER = "Roomba | Made by Legend#4321";
    public static int ERROR_EMBED_R = 255;
    public static int ERROR_EMBED_G = 20;
    public static int ERROR_EMBED_B = 20;

    public static MessageEmbed getInformationEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Roomba Discord Bot", "https://github.com/LegendWasTaken/Roomba");
        builder.setDescription("Roomba is a Discord JDA bot, developed by Legend#4321. It was made to create game rooms for tetris games online, from discord.");
        builder.setFooter("<3 If you're reading this have a nice day.");
        return builder.build();
    }

    public static MessageEmbed getErrorEmbed(ErrorType errorType) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(ERROR_EMBED_TITLE);
        builder.setColor(new Color(ERROR_EMBED_R, ERROR_EMBED_G, ERROR_EMBED_B));
        String errorDescription;
        switch(errorType) {
            case NOT_ENOUGH_ARGS:
                errorDescription = NOT_ENOUGH_ARGS_ERROR;
                break;
            case UNKNOWN_COMMAND:
                errorDescription = UNKNOWN_COMMAND_ERROR;
                break;
            case UNKNOWN_ERROR:
                errorDescription = UNKNOWN_ERROR_ERROR;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + errorType);
        }
        builder.setDescription(errorDescription);
        builder.setFooter(ERROR_EMBED_FOOTER);
        return builder.build();
    }

    public static MessageEmbed getHelpEmbed(DiscordCommand[] commands) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(HELP_EMBED_TITLE);
        builder.setColor(new Color(HELP_EMBED_R, HELP_EMBED_G, HELP_EMBED_B));

        StringBuilder commandsString = new StringBuilder();
        for (int i=0; i<commands.length; i++) {
            commandsString.append(HELP_EMBED_COMMAND_ENTRY_FORMAT
                    .replaceAll("\\{PREFIX}", PREFIX)
                    .replaceAll("\\{NAME}", commands[i].getCommand())
                    .replaceAll("\\{USAGE}", commands[i].getUsage()));
        }
        builder.setDescription(commandsString.toString());
        builder.setFooter(HELP_EMBED_FOOTER);
        return builder.build();
    }

    public static MessageEmbed getCommandInformationEmbed(DiscordCommand command) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(COMMAND_HELP_EMBED_TITLE);
        builder.setColor(new Color(COMMAND_HELP_EMBED_R, COMMAND_HELP_EMBED_G, COMMAND_HELP_EMBED_B));
        builder.setDescription(COMMAND_HELP_EMBED_COMMAND_FORMAT
                .replaceAll("\\{PREFIX}", PREFIX)
                .replaceAll("\\{NAME}", command.getCommand())
                .replaceAll("\\{DESCRIPTION}", command.getDescription())
                .replaceAll("\\{ALIASES}", Arrays.toString(command.getAliases()))
                .replaceAll("\\{USAGE}", command.getUsage()));
        builder.setFooter(COMMAND_HELP_EMBED_FOOTER);
        return builder.build();
    }
}
