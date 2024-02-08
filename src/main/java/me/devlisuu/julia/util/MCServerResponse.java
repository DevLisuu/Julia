package me.devlisuu.julia.util;

import java.util.List;

/**
 * @author zh32 <zh32 at zh32.de>
 */
@SuppressWarnings("unused")
public class MCServerResponse {
    private Description description;
    private Players players;
    private Version version;
    private String favicon;
    private int time;

    public Description getDescription() {
        return description;
    }

    public Players getPlayers() {
        return players;
    }

    public Version getVersion() {
        return version;
    }

    public String getFavicon() {
        return favicon;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static class Players {
        private int max;
        private int online;
        private List<Player> sample;

        public int getMax() {
            return max;
        }

        public int getOnline() {
            return online;
        }

        public List<Player> getSample() {
            return sample;
        }
    }

    public static class Player {
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

    }

    public static class Version {
        private String name;
        private int protocol;

        public String getName() {
            return name;
        }

        public int getProtocol() {
            return protocol;
        }
    }

    public static class Description {
        private String text;

        public String getText() {
            return text;
        }

    }
}
