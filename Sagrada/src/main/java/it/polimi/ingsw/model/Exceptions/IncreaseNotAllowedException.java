package it.polimi.ingsw.model.Exceptions;

public class IncreaseNotAllowedException extends Exception {
    private static final String msg = "29";
    public IncreaseNotAllowedException(){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
