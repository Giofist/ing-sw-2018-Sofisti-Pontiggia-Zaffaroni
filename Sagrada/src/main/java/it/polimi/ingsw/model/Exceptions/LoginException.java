package it.polimi.ingsw.model.Exceptions;

public class LoginException extends Exception {
    private static final String msg = "Login errato!";

    public LoginException() {
        super(msg);
    }

    public LoginException(String msg) {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}