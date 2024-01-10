package mancala;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String playerName;
    private Store playerStore;
    private UserProfile userProfile;

    // Default constructor
    public Player() {
        playerName = "Player";
        userProfile = new UserProfile(); 
    }

    public Player(final String name) {
        playerName = name;
        userProfile = new UserProfile(playerName);
    }

    // Get the name of the player
    public String getName() {
        return playerName;
    }

    // Get the player's store where they collect stones
    public Store getStore() {
        return playerStore;
    }

    // Get the count of the number of stones in the player's store
    public int getStoreCount() {
        return playerStore.getStoneCount();
    }

    // Get the player's user profile
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setStore(final Store store) {
        playerStore = store;
    }

    // String representation of the Player
    @Override
    public String toString() {
        return playerName;
    }
}