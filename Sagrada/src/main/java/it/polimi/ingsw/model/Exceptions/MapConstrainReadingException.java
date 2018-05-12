package it.polimi.ingsw.model.Exceptions;

public class MapConstrainReadingException extends Exception {
    private static final String msg = "Unable to read the map from the file Map.txt: id map";

    public MapConstrainReadingException(int idmap) {
        super(msg + idmap);
    }

    public MapConstrainReadingException(String msg) {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}