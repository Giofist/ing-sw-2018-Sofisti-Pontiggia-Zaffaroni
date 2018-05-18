package it.polimi.ingsw.model.Exceptions;

public class DecreaseNotAllowedException extends Exception {
    private static final String msg = "Non puoi diminuire il valore di questo dado\n";
    public DecreaseNotAllowedException() {
        super(msg);
    }
    public DecreaseNotAllowedException(String msg){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
