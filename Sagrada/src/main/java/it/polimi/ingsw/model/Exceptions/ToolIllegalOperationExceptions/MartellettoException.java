package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class MartellettoException extends ToolIllegalOperationException {
    private static final String msg = "Errore in martelletto";
    public MartellettoException() {
        super(msg);
    }
    public MartellettoException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
