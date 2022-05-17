package me.devlisuu.julia;

import me.devlisuu.julia.commands.CommandsManager;
import me.devlisuu.julia.listeners.SlashCommandInteraction;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.Date;

public class Julia {
    public static final Logger LOGGER = LoggerFactory.getLogger("Julia");
    public JDA jda;
    public static Date startDate;

    public Julia(String token) {
        try {
            jda = JDABuilder.createDefault(token)
                    .addEventListeners(new SlashCommandInteraction())
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.watching("Twitter"))
                    .build();
            jda.awaitReady();
            startDate = new Date();
        } catch(LoginException | InterruptedException e) {
            LOGGER.error("Login failed!");
            e.printStackTrace();
        }

        CommandsManager.createCommandMaps();
        Guild testGuild = jda.getGuildById(848556986102186015L);

        if (testGuild != null) {
            CommandsManager.registerGuildCommands(testGuild);
        }else{
            LOGGER.error("Test guild not found!");
        }
    }
}
