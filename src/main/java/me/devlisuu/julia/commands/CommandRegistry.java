package me.devlisuu.julia.commands;

import me.devlisuu.julia.Julia;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class CommandRegistry {
    public static void registerTestGuildCommands() {
        Julia.testGuild.updateCommands()
                .addCommands(Commands.slash("uptime", "Julia's uptime"))
                .addCommands(Commands.slash("memory", "Julia's memory usage"))
                .queue();
    }
}
