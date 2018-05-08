package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class PennelloPerPastaSaldaException extends ToolIllegalOperationException {
    private static final String msg = "Errore in pennello per pasta salda";
    public PennelloPerPastaSaldaException() {
        super(msg);
    }
    public PennelloPerPastaSaldaException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }

}
