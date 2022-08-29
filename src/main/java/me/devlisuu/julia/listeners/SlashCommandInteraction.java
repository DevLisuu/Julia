package me.devlisuu.julia.listeners;

import me.devlisuu.julia.Julia;
import me.devlisuu.julia.command.Command;
import me.devlisuu.julia.command.CommandsManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Map;

public class SlashCommandInteraction extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Map<String, Command> commandMap = CommandsManager.getGuildCommandMap();

        if(commandMap.containsKey(event.getName())) {
            Command command = commandMap.get(event.getName());

            try {
                boolean success = command.execute(event);
                if(!success) throw new Exception(); // what am I even doing here, to be fixed

                Julia.LOGGER.info(
                        String.format("%s ran %s command! (%s)", event.getUser().getName(), event.getName(), event.getOptions())
                );
            } catch(Exception e) {
                Julia.LOGGER.error(
                        String.format("Command execution failed! /%s %s)", event.getName(), event.getOptions())
                );

                // does not work if there is no original message
                event.getInteraction().getHook().editOriginal(String.format("Something went terribly wrong :moyai::moyai::moyai: [%s]", new Date().getTime())).queue();
                e.printStackTrace();
            }

        }
    }
}
