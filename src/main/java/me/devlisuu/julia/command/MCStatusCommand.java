package me.devlisuu.julia.command;

import me.devlisuu.julia.util.MCServerPing;
import me.devlisuu.julia.util.MCServerResponse;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;

public class MCStatusCommand extends Command{
    public MCStatusCommand() {
        name = "mcstatus";
        description = "Responds with info about Java Minecraft Server";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(this.name, this.description)
                .addOption(OptionType.STRING, "address", "Server's address", true);
                //.addOption(OptionType.NUMBER, "port", "Server's port", false);
    }

    @Override
    public boolean execute(SlashCommandInteractionEvent event) throws IOException {
        final String address = Objects.requireNonNull(event.getOption("address")).getAsString();

        event.deferReply(true).queue();
        MCServerPing theServer;
        theServer =  new MCServerPing(address);

        MCServerResponse data = theServer.fetchData();

        event.reply(String.format("Result: %s", data.getDescription().getText())).setEphemeral(true).queue();
        return true;
    }
}
