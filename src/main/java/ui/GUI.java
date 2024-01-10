package ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
//import javax.swing.border.Border;
import javax.swing.JLabel;
//import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import mancala.MancalaGame;
import mancala.InvalidMoveException;
import mancala.Player;

public class GUI extends JFrame {

    private MancalaGame game;
    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    private PositionAwareButton[][] buttons;
    
    
    public GUI (final String windowTitle) {
        super();
        game = new MancalaGame();

        setupPlayers();

        startUp(windowTitle);
        gameContainerStartUp();
        getContentPane().add(gameContainer, BorderLayout.CENTER);

        makeHeaderMenu();
        setJMenuBar(menuBar);

        getContentPane().add(setupActionPanel(), BorderLayout.EAST);

        pack();
        show();
    }

    //Hard-coded for testing
    private void setupPlayers() {
        // Create Player 1 with a predefined name and profile
        final String player1Name = "Player1";
        final Player player1 = new Player(player1Name);

        // Create Player 2 with a predefined name and profile
        final String player2Name = "Player2";
        final Player player2 = new Player(player2Name);

        // Set up the game with the predefined players
        game.setPlayers(player1, player2);
    }

    /* 
    public void playerStartUp() {
        JFrame playerFrame = new JFrame("Player Menu!");
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerFrame.setLayout(new BorderLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.PAGE_AXIS));

        JButton newPlayer = new JButton("Create New User");
        //newPlayer.addActionListener(event -> );
        playerPanel.add(newPlayer);

        JButton loadPlayer = new JButton("Choose User");
        //loadPlayer.addActionListener(event -> );
        playerPanel.add(loadPlayer);

        playerFrame.getContentPane().add(playerPanel, BorderLayout.CENTER);
    }*/

    /*
    private void mainMenu() {
        //popup when the program starts-- asks to create new user or not
        //If yes, input name player name
        //if no, load old userProfile
    } */

    private void startUp(final String title) {
        setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public void gameContainerStartUp(){
        gameContainer.add(makeMancalaPits(6, 2));
        //set up method for stores-- store 1 on the right of pits and store 2 on the left of pits
        //Figure out how to label the pits and stores
        //currentPlayer();
    }

    /* 
    private JLabel currentPlayer() {
        JLabel currentPlayer = new JLabel("Current Player: " + game.getCurrentPlayer().getName());
        gameContainer.add(currentPlayer, BorderLayout.NORTH);
    }*/

    private JPanel makeMancalaPits(final int wide, final int tall) {
        final JPanel pitPanel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        pitPanel.setLayout(new GridLayout(tall, wide));
        
        for (int y = 0; y < tall; y++) {
            for (int x = 0; x < wide; x++) {
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x + 1);
                buttons[y][x].setDown(y + 1);
                buttons[y][x].addActionListener(event-> {
                moveEvent(event);
                });
                pitButtonUpdate(buttons[y][x]); //set initial stone count
                pitPanel.add(buttons[y][x]);
            }
        }
        return pitPanel;
    }

    private void pitButtonUpdate(final PositionAwareButton button) {
        final int across = button.getAcross();
        final int down = button.getDown();

        final int pitNumber = (down - 1) * 6 + across;
    
        // Assuming you have a method to get stone count for a specific pit
        final int stoneCount = game.getBoard().getNumStones(pitNumber);
    
        // Set the text of the button to display the stone count
        button.setText(String.valueOf(stoneCount));
    }

    private void updatePitButtons() {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 6; x++) {
                pitButtonUpdate(buttons[y][x]);
            }
        }
    }

    private void moveEvent(final ActionEvent event) {
        final PositionAwareButton button = (PositionAwareButton) event.getSource();
        final int across = button.getAcross();
        final int down = button.getDown();
    
        int pitNumber = (down - 1) * 6 + across;

        try {
            game.move(pitNumber);
            updatePitButtons();
        } catch (InvalidMoveException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private JPanel setupActionPanel() {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.PAGE_AXIS));

        final JButton kalahButton = new JButton("New Kalah Board");
        kalahButton.addActionListener(event -> game.rulesSet(1));
        actionPanel.add(kalahButton);

        final JButton ayoButton = new JButton("New Ayoayo Board");
        kalahButton.addActionListener(event -> game.rulesSet(2));
        actionPanel.add(ayoButton);

        final JButton resetBoard = new JButton("Reset Current Game");
        resetBoard.addActionListener(event -> newGame());
        actionPanel.add(resetBoard);

        return actionPanel;
    }

    private void newGame() {
        game.startNewGame();
        updatePitButtons();
    }

    private void makeHeaderMenu() {
        menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem save = new JMenuItem("Save");
        //save.addActionListener(event -> saveGame(game));
        fileMenu.add(save);

        final JMenuItem load = new JMenuItem("Load");
        //load.addActionListener(event -> loadGame());
        fileMenu.add(load);

        menuBar.add(fileMenu);
    }

    public static void main(String[] args) {
        new GUI("Mancala!");
    }
} 
