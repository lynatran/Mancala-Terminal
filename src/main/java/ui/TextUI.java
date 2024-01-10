package ui;

import mancala.MancalaGame;
import mancala.PitNotFoundException;
import mancala.Player;

import mancala.GameNotOverException;
import mancala.InvalidMoveException;

import files.Saver;
import java.io.IOException;

import java.io.Serializable;

import java.util.Scanner;

public class TextUI {
    private MancalaGame game;
    private Scanner scanner;
    private Saver serialize;

    public TextUI() {
        game = new MancalaGame();
        scanner = new Scanner(System.in);
        serialize = new Saver();
    }

    public void startGame() {
        System.out.println("=== Welcome to Mancala! ===");

        /* 
        System.out.println("Load in Game? Y/N: ");
        String loadChoice = scanner.nextLine();

        if (loadChoice.equals("Y")) {
            loadGame();
        } */

        System.out.println("Choose 1 for Kalah, 2 for Ayoayo:");
        String rulesChoice = scanner.nextLine();
        int ruleNum = Integer.parseInt(rulesChoice);
        game.rulesSet(ruleNum);

        System.out.println("Enter player one's name:");
        String playerOneName = scanner.nextLine();
        Player playerOne = new Player(playerOneName);
        System.out.println("Enter player two's name:");
        String playerTwoName = scanner.nextLine();
        Player playerTwo = new Player(playerTwoName);
        game.setPlayers(playerOne, playerTwo);
        game.setCurrentPlayer(playerOne);
        
        game.getBoard();

        boolean validChoice = false;
        int startPit = 0;


        while (true) {
            displayBoard();

            if (game.isGameOver()) {
                displayGameResult();
                break;
            }
            
            /* Serlization to save mancala game

            System.out.println("Would you like save the game? Y/N:");
            String yesOrNo = scanner.nextLine();

            if (yesOrNo.equals("Y")) {
                // Save the game before exiting
                saveGame();

                System.out.println("Want to exit?");
                String exitYN = scanner.nextLine();

                if (exitYN.equals("Y")) {
                    break;
                }
            }
            */

            validChoice = false;
            while (!validChoice) {
                System.out.println("\nEnter the pit number (1-6 for Player 1, 7-12 for Player 2):");
                String startPitString = scanner.nextLine();   //Entering pit #
                startPit = Integer.parseInt(startPitString);
                validChoice = isValidPitChoice(startPit);
            }

            try {
                int stonesRemaining = game.move(startPit);

                if (stonesRemaining == -1) {
                    System.out.println("Free turn, go again!");
                } else {
                    if (game.getCurrentPlayer() == playerOne) {
                        game.setCurrentPlayer(playerTwo);
                        game.getBoard().setPlayer(2);
                    } else {
                        game.setCurrentPlayer(playerOne);
                        game.getBoard().setPlayer(1);
                    }
                }
                
            } catch (InvalidMoveException err) {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    private boolean isValidPitChoice(int startPit) {
        Player currentPlayer = game.getCurrentPlayer();
        if (currentPlayer == game.getPlayers().get(0) && (startPit < 1 || startPit > 6)) {
            System.out.println("\nWrong pit side!");
        } else if (currentPlayer == game.getPlayers().get(1)  && (startPit > 12 || startPit < 7)) {
            System.out.println("\nWrong pit side!");
        } else if (game.getBoard().getNumStones(startPit) == 0) {
            System.out.println("\nChosen pit has no stones! Try another pit.");
        } else {
            return true;
        }
        return false;
    }

    private void displayBoard() {
        System.out.println(game.toString());
    }

    private void displayGameResult() {
        try {
            Player winner = game.getWinner();
            if (winner == null) {
                System.out.println("It's a tie!");
            } else {
                System.out.println("Player " + winner.getName() + " wins!");
            }
        } catch (GameNotOverException err) {
            System.out.println("The game is not over yet.");
        } catch (PitNotFoundException err) {
            System.out.println("The pit is not found.");
        }
    }

    /*
    private void saveGame() {
        try {
            serialize.saveObject(game, "game");
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    private void loadGame() {
        try {
            serialize.loadObject("game");
            System.out.println("Game loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    } */
    public static void main(String[] args) {

        TextUI textUI = new TextUI();

        // Start or resume the game
        textUI.startGame();
    
    }
}