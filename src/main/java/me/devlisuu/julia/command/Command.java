package me.devlisuu.julia.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.io.IOException;

public abstract class Command {
    protected String name;
    protected String description;

    public String getCommandName() {
        return this.name;
    }

    protected abstract CommandData getCommandData();

    public abstract boolean execute(SlashCommandInteractionEvent event) throws IOException;
}
