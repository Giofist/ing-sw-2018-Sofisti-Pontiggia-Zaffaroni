package it.polimi.ingsw.model.Exceptions;

public class ToolActionNotIntheSetOfCostsException extends Exception {
    private static final String msg = "questa tool action non ha costo\n";
    public ToolActionNotIntheSetOfCostsException() {
        super(msg);
    }
    public ToolActionNotIntheSetOfCostsException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
