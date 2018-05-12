package it.polimi.ingsw.model.Exceptions;

public class IncreaseNotAllowedException extends Exception {
    private static final String msg = "Increase not allowed";
    public IncreaseNotAllowedException(String msg){
        super(msg);
    }
    public IncreaseNotAllowedException(){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}