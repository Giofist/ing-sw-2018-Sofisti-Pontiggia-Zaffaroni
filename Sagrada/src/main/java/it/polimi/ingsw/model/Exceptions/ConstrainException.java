package it.polimi.ingsw.model.Exceptions;

public class ConstrainException extends Exception {
    private static final String msg = "Unable to read the map from the file Map.txt";

    public ConstrainException() {
        super(msg);
    }

    public ConstrainException(String msg) {
        super(msg);
    }
}