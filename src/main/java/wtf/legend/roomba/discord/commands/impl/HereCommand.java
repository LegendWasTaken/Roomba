package wtf.legend.roomba.discord.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import wtf.legend.roomba.discord.Lang;
import wtf.legend.roomba.discord.commands.DiscordCommand;

import java.util.Arrays;
import java.util.HashMap;

public class HereCommand implements DiscordCommand {

    private HashMap<Long, Long> timeMaps = new HashMap<Long, Long>();
    private Long coolDown;

    public HereCommand() {
        this.timeMaps.put(674872007925432331L, 0L);
        this.timeMaps.put(674872208585130004L, 0L);
        this.timeMaps.put(674872351862685706L, 0L);
        this.timeMaps.put(674872385014333440L, 0L);
        this.timeMaps.put(674872406556409867L, 0L);
        this.coolDown = 30L * 60L * 1000L;
    }

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
        if (this.timeMaps.containsKey(channel.getIdLong())) {
            long timeLastSent = this.timeMaps.get(channel.getIdLong());
            long currentTime = System.currentTimeMillis();
            if (timeLastSent + this.coolDown > currentTime) {
                // Still under cooldown
                channel.sendMessage(Lang.getCooldownEmbed(timeLastSent + this.coolDown - currentTime)).queue();
            } else {
                this.timeMaps.put(channel.getIdLong(), currentTime);
                StringBuilder builder = new StringBuilder();
                for (int i=0; i<args.length; i++) {
                    builder.append(args[i]).append(" ");
                }
                channel.sendMessage("@here").queue();
                //channel.sendMessage(Lang.getPingEmbed(author.getEffectiveName(), builder.toString())).queue();
            }
        }
    }

    public int requiredArgs() {
        return 0;
    }

    public String[] getAliases() {
        return new String[] {};
    }
}
