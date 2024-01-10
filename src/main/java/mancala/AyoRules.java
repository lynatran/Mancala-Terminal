package mancala;

public class AyoRules extends GameRules {

   // Capture stones from the opponent's pits
   @Override
   public int captureStones(final int stoppingPoint) {

        // Calculate the index of the opposite pit
        final int oppositePitIndex = 13 - stoppingPoint;

        final int stoppingPit = getDataStructure().getNumStones(stoppingPoint);
        final int oppositePitNum = getDataStructure().getNumStones(oppositePitIndex);
    
        // Check conditions for capturing stones
        if (stoppingPit == 1 && oppositePitNum > 0) {
            // Capture stones and print a message
            final int capturedStones = getDataStructure().removeStones(oppositePitIndex);
            System.out.println("Captured " + capturedStones + " stones!\n");
            return capturedStones;
        }
    
        return 0;
    }

    // Helper method that distributes stones into pits and stores
    // Skipping the opponent's store
    @Override
    public int distributeStones(final int startingPoint) {
        // Get the starting pit using the iterator
        getDataStructure().setIterator(startingPoint, getCurrentPlayer(), true);
        int stoneAmount = getDataStructure().removeStones(startingPoint);
        int currentIndex = startingPoint;
        int totalStonesAdded = 0;

        while (stoneAmount > 0) {
            Countable currentPit = getDataStructure().next();
            currentIndex = currentIndex % 13 + 1; // Increment and wrap around from 1 to 12
           
            currentPit.addStone();
            stoneAmount--;
            totalStonesAdded++;
        }

        // Check if the last stone lands in a non-empty pit
        if (getDataStructure().getNumStones(currentIndex) > 1) {
        // Collect and redistribute stones in the current pit
        totalStonesAdded += distributeStones(currentIndex);
    }

        return totalStonesAdded;
    }

    // Moves stones for the player starting from a specific pit
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException();
        }

        final int prevStoreCount = getDataStructure().getStoreCount(playerNum);
    
        // Distribute the remaining stones
        final int totalStonesAdded = distributeStones(startPit);
        int stopPit = (startPit + totalStonesAdded) % 12;

        if (playerNum == 1 && stopPit > 6) {
            stopPit--;
        } else if (playerNum == 2 && stopPit < 7) {
            stopPit--;
        }
        
        // Check if the last stone stops on the current player's side
        final boolean captureCheck = captureCheck(playerNum, stopPit);

        if (captureCheck) {
            // Capture any stones from the opponent's pits
            final int stonesCaptured = captureStones(stopPit);
            getDataStructure().addToStore(playerNum, stonesCaptured);
        }

        final int addedStoreCount = getDataStructure().getStoreCount(playerNum) - prevStoreCount;

        // Return the total number of stones added to the corresponding store
        return addedStoreCount;
    }

    private boolean captureCheck(final int playerNum, final int stopPit) {
        return playerNum == 1 && stopPit < 7 || playerNum == 2 && stopPit > 7;
    }
}