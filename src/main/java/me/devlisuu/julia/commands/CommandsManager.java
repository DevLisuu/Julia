package me.devlisuu.julia.commands;

import me.devlisuu.julia.Julia;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandsManager {
    protected static Map<String, Command> guildCommands;

    public static Map<String, Command> getGuildCommandMap() {
        return guildCommands;
    }

    public static void createCommandMaps() {
        guildCommands = new HashMap<>();

        CommandsManager.addCommandToGuildMap(new UptimeCommand());
        CommandsManager.addCommandToGuildMap(new MemoryCommand());
    }

    private static void addCommandToGuildMap(Command commandClass) {
        guildCommands.put(commandClass.getCommandName(), commandClass);
    }

    public static void registerGuildCommands(Guild guild) {
        List<CommandData> commandDataList = CommandsManager.getGuildCommandMap().values()
                .stream().map(Command::getData).toList();

        guild.updateCommands().addCommands(commandDataList).queue();
        Julia.LOGGER.info("Guild commands registered successfully!");
    }
}
