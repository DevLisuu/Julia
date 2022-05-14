package me.devlisuu.julia.listeners;

import me.devlisuu.julia.Julia;
import me.devlisuu.julia.util.TimeFormater;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class SlashCommandInteraction extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Julia.LOGGER.debug(event.getName());
        switch(event.getName()) {
            case "uptime":
                long uptime = new Date().getTime() - Julia.botStartDate.getTime();
                event.reply(String.format("Uptime: %s", TimeFormater.formatTime(uptime))).setEphemeral(true).queue();
                break;
            case "memory":
                long memoryUsageBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                long memoryUsageKiloBytes = memoryUsageBytes / 1024;
                long memoryUsageMegaBytes = memoryUsageKiloBytes / 1024;
                event.reply(String.format("Memory usage: %smb", memoryUsageMegaBytes)).setEphemeral(true).queue();
                break;
        }
    }
}
