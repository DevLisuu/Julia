package me.devlisuu.julia.commands;

import me.devlisuu.julia.Julia;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.HashMap;
import java.util.Map;

public class CommandsManager {
    protected static Map<String, Command> commands;

    public static Map<String, Command> getCommandMap() {
        return commands;
    }

    public static void createCommandMaps() {
        commands = new HashMap<>();
        CommandsManager.addCommandToGuildMap(new UptimeCommand());
        CommandsManager.addCommandToGuildMap(new MemoryCommand());
    }

    private static void addCommandToGuildMap(Command commandClass) {
        commands.put(commandClass.getCommandName(), commandClass);
    }

    public static void registerGuildCommands(Guild guild) {
        CommandListUpdateAction commandList = guild.updateCommands();

        for(Map.Entry<String, Command> commandData: commands.entrySet()) {
            Command commandClass = commandData.getValue();
            commandList = commandList.addCommands(Commands.slash(commandClass.getCommandName(), commandClass.getCommandDescription()));
        }
        commandList.queue();

        Julia.LOGGER.info("Guild commands registered successfully!");
    }
}
