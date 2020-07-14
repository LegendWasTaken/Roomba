package wtf.legend.roomba.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Lang {

    /* Embed Lang */
    public static String EMBED_FOOTER = "Roomba | Made by Legend#4321";

    /* Error Embeds */
    public static String ERROR_TITLE = "Error";
    public static Color ERROR_COLOUR = new Color(255, 20, 20);

    public static String NOT_ENOUGH_ARGS_DESC = "You need to specify more arguments!";

    // Color is spelt wrong

    public static MessageEmbed getInformationEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Roomba Discord Bot", "https://github.com/LegendWasTaken/Roomba");
        builder.setDescription("Roomba is a Discord JDA bot, developed by Legend#4321. It was made to create game rooms for tetris games online, from discord.");
        builder.setFooter("<3 If you're reading this have a nice day.");
        return builder.build();
    }

    public static MessageEmbed getNotEnoughArgsEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(ERROR_TITLE);
        builder.setColor(ERROR_COLOUR);
        builder.setFooter(EMBED_FOOTER);
        return builder.build();
    }

}
