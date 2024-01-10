/* Code function prototypes provided by CIS*2430 Instructor, Dr. Judi McCuaig*/
/* Functions coded by Lyna Tran */

package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {
    private static final long serialVersionUID = 1L;

    final private MancalaDataStructure gameBoard;
    private int currentPlayer; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        currentPlayer = 1;
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(final int pitNum) {
        if (pitNum < 7) {
            for (int i = 1; i < 7; i++) {
                //System.out.println("PIT #:" + i);
                if (gameBoard.getNumStones(i) > 0) {
                    return false;
                }
            }
        } else {
            for (int i = 8; i < 13; i++) {
                //System.out.println("PIT #:" + i);
                if (gameBoard.getNumStones(i) > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // Create stores for players and set them on the board
        final Store storePlayerOne = new Store();
        final Store storePlayerTwo = new Store();

        // Set the owner of the stores
        storePlayerOne.setOwner(one);
        storePlayerTwo.setOwner(two);
        one.setStore(storePlayerOne);
        two.setStore(storePlayerTwo);

        // Set the stores on the board
        gameBoard.setStore(storePlayerOne, 1);
        gameBoard.setStore(storePlayerTwo, 2);
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.clearPits();
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        final StringBuilder gameString = new StringBuilder();
        gameString.append("\nGame Board:\n").append(gameBoard.toString()).append("\n");
        return gameString.toString();
    }
}
