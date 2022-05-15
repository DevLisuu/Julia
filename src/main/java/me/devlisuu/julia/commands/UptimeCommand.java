package me.devlisuu.julia.commands;

import me.devlisuu.julia.Julia;
import me.devlisuu.julia.util.TimeFormater;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Date;

public class UptimeCommand extends Command{
    public UptimeCommand() {
        name = "uptime";
        description = "Julia's uptime";
    }

    @Override
    public boolean execute(SlashCommandInteractionEvent event) {
        long uptime = new Date().getTime() - Julia.startDate.getTime();
        event.reply(String.format("Uptime: %s", TimeFormater.formatTime(uptime))).setEphemeral(true).queue();
        return true;
    }
}
