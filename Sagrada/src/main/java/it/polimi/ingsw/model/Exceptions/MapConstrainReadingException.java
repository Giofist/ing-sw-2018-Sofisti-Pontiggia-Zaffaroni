package it.polimi.ingsw.model.Exceptions;

public class MapConstrainReadingException extends Exception {
    private static final String msg = "Unable to read the map from the file Map.txt";

    public MapConstrainReadingException() {
        super(msg);
    }

    public MapConstrainReadingException(String msg) {
        super(msg);
    }
}