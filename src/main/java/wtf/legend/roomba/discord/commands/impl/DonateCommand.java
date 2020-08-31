package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.commands.DiscordCommand;

public class DonateCommand implements DiscordCommand {
    public String getCommand() {
        return "donate";
    }

    public String getUsage() {
        return "N/A";
    }

    public String getDescription() {
        return "Donate to me, the creator of this bot and buy me a cup of coffee <3";
    }

    public void execute(MessageChannel channel, Member author, String command, String... args) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Donate to the creator of Roomba!", "https://www.paypal.me/caiowastaken");
        builder.setDescription("While the code for this bot is open source, hosting it isn't free for me. Help me out with server costs and buy me a coffee!");
        channel.sendMessage(builder.build()).queue();
    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {"sendmemoney", "ineedtofeedmychildren"};
    }
}
