package me.devlisuu.julia.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Objects;

public class SayCommand extends Command{
    public SayCommand() {
        name = "say";
        description = "Julia will reply with the same message";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(this.name, this.description)
                .addOption(OptionType.STRING, "message", "Bot's reply message", true);
    }

    @Override
    public boolean execute(SlashCommandInteractionEvent event) {
        final String message = Objects.requireNonNull(event.getOption("message")).getAsString();
        event.reply(String.format("Your message was: %s", message)).setEphemeral(true).queue();
        return true;
    }
}
