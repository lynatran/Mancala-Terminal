package mancala;

import java.io.Serializable;

public class Pit implements Serializable, Countable {
    private static final long serialVersionUID = 1L;

    private int stoneCount;

    // Constructor
    public Pit() {
        stoneCount = 0;
    }

    // Add a stone to the pit
    @Override
    public void addStone() {
        addStones(1);
    }

    @Override
    public void addStones(final int amount) {
        for (int i = 0; i < amount; i++) {
            stoneCount++;
        }
    }

    // Get the number of stones in the pit
    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    // Remove and return the stones from the pit
    @Override
    public int removeStones() {
        final int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }
}
