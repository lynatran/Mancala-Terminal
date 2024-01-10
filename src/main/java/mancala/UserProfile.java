package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int kalahGamesWon;
    private int ayoGamesWon;

    public UserProfile() {
        userName = "New User";
        kalahGamesPlayed = 0;
        ayoGamesPlayed = 0;
        kalahGamesWon = 0;
        ayoGamesWon = 0;
    }

    public UserProfile(final String name) {
        userName = name;
        kalahGamesPlayed = 0;
        ayoGamesPlayed = 0;
        kalahGamesWon = 0;
        ayoGamesWon = 0;
    }

    public void setUserName(final String name) {
        userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public int getKalahPLayed() {
        return kalahGamesPlayed;
    }

    public void increaseKalahPlayed() {
        kalahGamesPlayed++;
    }

    public int getKalahWon() {
        return kalahGamesWon;
    }

    public void increaseKalahWon() {
        kalahGamesWon++;
    }

    public int getAyoPLayed() {
        return ayoGamesPlayed;
    }

    public void increaseAyoPlayed() {
        ayoGamesPlayed++;
    }

    public int getAyoWon() {
        return ayoGamesWon;
    }

    public void increaseAyoWon() {
        ayoGamesWon++;
    }

    @Override
    public String toString() {
        final StringBuilder userString = new StringBuilder();

        userString.append("User: " + userName + "\n");
        userString.append("Kalah Games Played: " + kalahGamesPlayed + "\n");
        userString.append("Kalah Games Won: " + kalahGamesWon + "\n");
        userString.append("Ayoayo Games Played: " + ayoGamesPlayed + "\n");
        userString.append("Ayoayo Games Won: " + ayoGamesWon + "\n");

        return userString.toString();
    }
}
