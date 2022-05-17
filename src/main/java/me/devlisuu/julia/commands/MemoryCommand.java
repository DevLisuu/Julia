package me.devlisuu.julia.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class MemoryCommand extends Command{
    public MemoryCommand() {
        name = "memory";
        description = "Julia's memory usage";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(this.name, this.description);
    }

    @Override
    public boolean execute(SlashCommandInteractionEvent event) {
        long memoryUsageBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoryUsageKiloBytes = memoryUsageBytes / 1024;
        long memoryUsageMegaBytes = memoryUsageKiloBytes / 1024;

        event.reply(String.format("Memory usage: %smb", memoryUsageMegaBytes)).setEphemeral(true).queue();
        return true;
    }
}
