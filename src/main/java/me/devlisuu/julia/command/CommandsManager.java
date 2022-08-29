package me.devlisuu.julia.command;

import me.devlisuu.julia.Julia;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandsManager {
    public static final Logger LOGGER = LoggerFactory.getLogger("CommandsManager");
    protected static Map<String, Command> guildCommands;

    public static Map<String, Command> getGuildCommandMap() {
        return guildCommands;
    }

    public static void createCommandMaps() {
        guildCommands = new HashMap<>();

        CommandsManager.addCommandToGuildMap(new UptimeCommand());
        CommandsManager.addCommandToGuildMap(new MemoryCommand());
        CommandsManager.addCommandToGuildMap(new SayCommand());
        CommandsManager.addCommandToGuildMap(new MCStatusCommand());
    }

    private static void addCommandToGuildMap(Command commandClass) {
        if(guildCommands.containsKey(commandClass.getCommandName())) {
            LOGGER.warn(
                    String.format("Command with the name \"%s\" was already in the map", commandClass.getCommandName())
            );
        }

        guildCommands.put(commandClass.getCommandName(), commandClass);
    }

    public static void registerGuildCommands(Guild guild) {
        List<CommandData> commandDataList = CommandsManager.getGuildCommandMap().values()
                .stream().map(Command::getCommandData).toList();

        guild.updateCommands().addCommands(commandDataList).queue();
        LOGGER.info("Guild commands registered successfully!");
    }
}
