package me.devlisuu.julia.listeners;

import me.devlisuu.julia.Julia;
import me.devlisuu.julia.commands.Command;
import me.devlisuu.julia.commands.CommandsManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SlashCommandInteraction extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Map<String, Command> commandMap = CommandsManager.getCommandMap();

        if(commandMap.containsKey(event.getName())) {
            Command command = commandMap.get(event.getName());

            boolean result = command.execute(event);
            if(result) {
                Julia.LOGGER.info(String.format("%s ran command! (%s)", event.getUser().getName(), event.getName()));
            }else {
                Julia.LOGGER.error(String.format("Command execution failed! (%s)", event));
            }
        }
    }
}
