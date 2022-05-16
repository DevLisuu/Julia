package me.devlisuu.julia.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public abstract class Command {
    protected String name;
    protected String description;

    public String getCommandName() {
        return this.name;
    }

    public abstract boolean execute(SlashCommandInteractionEvent event);

    protected CommandData getData() {
        return Commands.slash(this.name, this.description);
    }
}
