package it.polimi.ingsw.model.Exceptions;

public class CardIdNotAllowedException extends Exception {
    private static final String msg = "23";
    public CardIdNotAllowedException() {
        super(msg);
    }
    public CardIdNotAllowedException(String msg){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
