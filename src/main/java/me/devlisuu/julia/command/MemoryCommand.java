package me.devlisuu.julia.command;

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
        long memoryUsageKilobytes = memoryUsageBytes / 1024;
        long memoryUsageMegabytes = memoryUsageKilobytes / 1024;

        event.reply(String.format("Memory usage: %smb", memoryUsageMegabytes)).setEphemeral(true).queue();
        return true;
    }
}
