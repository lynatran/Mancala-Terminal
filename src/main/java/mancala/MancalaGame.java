package mancala;

import java.util.ArrayList;
import java.io.Serializable;

public class MancalaGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private GameRules board;
    private Player currentPlayer;
    final private ArrayList<Player> players;

      /**
     *  Game constructor
     */
    public MancalaGame() {
        board = new KalahRules();
        players = new ArrayList<>();
        currentPlayer = null;
    }

      /**
     * Sets game rules
     *
     * @param choice  Rules choice, 1 = Kalah and 2 = Ayo
     */
    public void rulesSet(final int choice) {
        switch (choice) {
            case 1:
                changeRules(new KalahRules());
                break;
            case 2:
                changeRules(new AyoRules());
                break;
            default:
                changeRules(new KalahRules());
        }
    }

    private void changeRules(final GameRules newBoard) {
        board = newBoard;
    }

      /**
     * Gets the board for the game
     *
     * @return The game board
     */
    public GameRules getBoard() {
        return board;
    }

  /**
     * Gets current player
     *
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

  /**
     * Gets both players
     *
     * @return both players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

      /**
     * Tells the number of stones in a pit
     *
     * @param pitNum   The number of the pit.
     * @return The current number of stones in the pit.
     */
    public int getNumStones(final int pitNum) throws PitNotFoundException {

        if (pitNum < 1 || pitNum > 12) {
            throw new PitNotFoundException();
        }

        return board.getNumStones(pitNum);
    }

      /**
     * Gets the total number of stones in a player's store
     *
     * @param player   owner of pit
     * @return The current number of stones in the store.
     */
    public int getStoreCount(final Player player) throws NoSuchPlayerException {
        if (player.equals(players.get(0))) {
            return board.getDataStructure().getStoreCount(1);
        } else if (player.equals(players.get(1))) {
            return board.getDataStructure().getStoreCount(2);
        } else {
            throw new NoSuchPlayerException();
        }
    }

    
    /**
     * Gets the winner of the game
     *
     * @return The winner
     */
    public Player getWinner() throws GameNotOverException, PitNotFoundException {
        if (!isGameOver()) {
            throw new GameNotOverException();
        }

        collectingStones();
        
        int storeCountOne = 0;
        int storeCountTwo = 0;

        try {
            storeCountOne = getStoreCount(players.get(0));
            storeCountTwo = getStoreCount(players.get(1));
        } catch (NoSuchPlayerException e) {
            System.err.println("NoSuchPlayerException: " + e.getMessage());
            throw new GameNotOverException();
        }
    
        printResults(storeCountOne, storeCountTwo);
    
        if (storeCountOne > storeCountTwo) {
            return players.get(0);
        } else if (storeCountTwo > storeCountOne) {
            return players.get(1);
        } else {
            return null;  // It's a tie!
        }
    }

    private void collectingStones() {
        int oneRemaining = 0;
        int twoRemaining = 0;
    
        for (int i = 1; i < 7; i++) {
            oneRemaining = oneRemaining + board.getNumStones(i);
        }
        players.get(0).getStore().addStones(oneRemaining);
    
        for (int i = 7; i < 12; i++) {
            twoRemaining = twoRemaining + board.getNumStones(i);
        }
        players.get(1).getStore().addStones(twoRemaining);
    }

    private void printResults(final int storeCountOne, final int storeCountTwo) {
        System.out.println("=== Final Results ===");
        System.out.println(players.get(0).getName() + "'s store has " + storeCountOne + " stones");
        System.out.println(players.get(1).getName() + "'s store has " + storeCountTwo + " stones\n");
    }

    /**
     * Checks if game over
     *
     * @return true or false for game over
     */
    public boolean isGameOver() {
        // Implement game over condition (e.g., when one side is empty)
        return board.isSideEmpty(1) || board.isSideEmpty(7);
    }

    /**
     * Makes move for current player
     *
     * @param startPit  Beginning pit
     * @return stones reminaing in current player's side
     */
    public int move(final int startPit) throws InvalidMoveException {

        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException();
        }

        int playerNum = 0;

        if (currentPlayer == players.get(0)) {
            playerNum = 1;
        } else if (currentPlayer == players.get(1)) {
            playerNum = 2;
        }

        final int stonesCaptured = board.moveStones(startPit, playerNum);

        try {
            final int remainingStones = countRemainders(startPit);
            
            if (stonesCaptured == -1) {
                return -1;
            }
            return remainingStones;
        } catch (PitNotFoundException err) {
            throw new InvalidMoveException();
        }
    }

    private int countRemainders(final int startPit) throws PitNotFoundException {
        int remainders = 0;

        if (startPit >= 1 && startPit < 7) {
            for (int i = 1; i < 7; i++) {
                remainders = remainders + board.getNumStones(i);
            }
        } else if (startPit >= 7 && startPit < 13) {
            for (int i = 7; i < 13; i++) {
                remainders = remainders + board.getNumStones(i);
            }
        }
        System.out.println("Stones remaining in your pits: " + remainders + "\n");
        return remainders;
    }

     /**
     * Sets board
     *
     */
    public void setBoard(final GameRules theBoard) {
        board = theBoard;
    }

    /**
     * Sets current player
     *
     */
    public void setCurrentPlayer(final Player player) {
        currentPlayer = player;
    }

    /**
     * Sets players for game
     *
     * @param onePlayer   player 1
     * @param twoPlayer   player 2
     */
    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        players.clear();
        players.add(onePlayer);
        players.add(twoPlayer);

        board.registerPlayers(onePlayer, twoPlayer);
        currentPlayer = players.get(0);
    }

    /**
     * Starts new game
     *
     */
    public void startNewGame() {
        board.resetBoard();
        currentPlayer = players.get(0);
    }

    /**
     * String representation of game
     */
    @Override
    public String toString() {
    final StringBuilder gameString = new StringBuilder();

    // Display the board
    gameString.append(board);

    // Display the current player
    gameString.append("Current Player: ").append(currentPlayer);

    return gameString.toString();
    }
}