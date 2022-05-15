package me.devlisuu.julia.commands;

import me.devlisuu.julia.Julia;
import me.devlisuu.julia.util.TimeFormater;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Date;

public class MemoryCommand extends Command{
    public MemoryCommand() {
        name = "memory";
        description = "Julia's memory usage";
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
