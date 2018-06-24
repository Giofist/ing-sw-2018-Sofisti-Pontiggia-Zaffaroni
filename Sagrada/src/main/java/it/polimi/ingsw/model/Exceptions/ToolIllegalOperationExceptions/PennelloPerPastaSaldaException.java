package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class PennelloPerPastaSaldaException extends ToolIllegalOperationException {
    private static final String msg = "16";
    public PennelloPerPastaSaldaException() {
        super(msg);
    }
    public PennelloPerPastaSaldaException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }

}
