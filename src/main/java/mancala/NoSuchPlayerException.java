package mancala;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("The player is not found!");
    }
}
