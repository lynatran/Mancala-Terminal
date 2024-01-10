package mancala;

import java.io.Serializable;

public class Store implements Serializable, Countable {
    private static final long serialVersionUID = 1L;

    private int totalStones;
    private Player owner;

    // Constructor
    public Store() {
        totalStones = 0;
        owner = null;
    }

    // Add stones to the store
    @Override
    public void addStones(final int amount) {
        totalStones = totalStones + amount;
    }

    @Override
    public void addStone() {
        addStones(1);
    }

    // Empty the store and return the number of stones in it
    @Override
    public int removeStones() {
        totalStones = 0;
        return totalStones;
    }

    // Get the owner of the store
    public Player getOwner() {
        return owner;
    }

    // Get the total number of stones in the store
    @Override
    public int getStoneCount() {
        return totalStones;
    }

    // Set the owner of the store
    public void setOwner(final Player player) {
        owner = player;
    }

    @Override
    public String toString() {
        return "Player: " + owner.getName() + " with " + totalStones;
    }
}