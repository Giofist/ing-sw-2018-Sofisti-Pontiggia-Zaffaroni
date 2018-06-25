package it.polimi.ingsw.model.Exceptions;

public class PlayerNotFoundException extends Exception {
    private static final String msg = "38";

    public static String getMsg() {
        return msg;
    }
}
