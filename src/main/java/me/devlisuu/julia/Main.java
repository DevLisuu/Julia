package me.devlisuu.julia;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Put your bot token as first java argument!");
            System.exit(1);
        }

        new Julia(args[0]);
    }
}
