package me.devlisuu.julia.commands;

import me.devlisuu.julia.Julia;

public class CommandRegistry {
    public static void registerTestGuildCommands() {
        Julia.testGuild.upsertCommand("uptime", "Julia's uptime").queue();
        Julia.testGuild.upsertCommand("memory", "Julia's memory usage").queue();
    }
}
