package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class TaglierinaCircolareException extends ToolIllegalOperationException {
    private static final String msg = "Errore in taglierina circolare\n";
    public TaglierinaCircolareException() {
        super(msg);
    }
    public TaglierinaCircolareException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }

}
