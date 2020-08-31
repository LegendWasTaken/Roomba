package wtf.legend.roomba.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import wtf.legend.roomba.discord.commands.DiscordCommand;
import wtf.legend.roomba.discord.commands.impl.*;
import wtf.legend.roomba.discord.events.MessageEvent;

import javax.security.auth.login.LoginException;

public class DiscordClient {

    private DiscordCommand[] commands;
    JDABuilder builder;
    JDA jda;

    public String getId() {
        return this.jda.getSelfUser().getId();
    }

    public DiscordClient(String token) throws LoginException {
        // Starting the bot up with intentions
        this.builder = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES);

        /* Events */
        this.builder.addEventListeners(new MessageEvent(this));

        /* Build Discord */
        this.jda = this.builder.build();

        /* Register Commands */
        this.commands = new DiscordCommand[] {
                new CreateRoomCommand(),
                new HelpCommand(),
                new HereCommand(),
                new InfoCommand(),
                new PingCommand(),
                new DonateCommand()
        };

        // UNchEcKEd CaSt AnD HarD cOdeD
        // Cool I don't care.
        ((HelpCommand) this.commands[1]).setCommands(this.commands);

    }

    public void commandEvent(MessageChannel channel, Member author, String command, String... args) {
        for (int i=0; i<this.commands.length; i++) {
            boolean isCommand = this.commands[i].getCommand().equals(command);
            String[] aliases = this.commands[i].getAliases();
            for (int a=0; a<aliases.length; a++) {
                if (aliases[a].equals(command)) {
                    isCommand = true;
                    break;
                }
            }

            if (isCommand) {
                if (this.commands[i].requiredArgs() > args.length) {
                    channel.sendMessage(Lang.getErrorEmbed(ErrorType.NOT_ENOUGH_ARGS)).queue();
                } else {
                    this.commands[i].execute(channel, author, command, args);
                }
                break;
            }
        }
    }

}
