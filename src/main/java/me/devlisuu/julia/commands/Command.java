package me.devlisuu.julia.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class Command {
    protected String name;
    protected String description;

    public String getCommandName() {
        return this.name;
    }
    public String getCommandDescription() {
        return this.description;
    }

    public abstract boolean execute(SlashCommandInteractionEvent event);
}
