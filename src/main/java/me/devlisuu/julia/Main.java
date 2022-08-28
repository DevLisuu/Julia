package me.devlisuu.julia;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String TOKEN;

        if(dotenv.get("TOKEN") == null) {
            if(args.length < 1) {
                System.out.println("Put your bot token as first java argument!");
                System.exit(1);
            }
            TOKEN = args[0];
        }else {
            TOKEN = dotenv.get("TOKEN");
        }

        new Julia(TOKEN);
    }
}
