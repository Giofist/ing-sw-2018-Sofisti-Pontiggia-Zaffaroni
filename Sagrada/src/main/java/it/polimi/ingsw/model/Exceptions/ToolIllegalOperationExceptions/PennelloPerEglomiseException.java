package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class PennelloPerEglomiseException extends ToolIllegalOperationException {
    private static final String msg = "Errore in Pennello per Eglomise";
    public PennelloPerEglomiseException() {
        super(msg);
    }
    public PennelloPerEglomiseException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
