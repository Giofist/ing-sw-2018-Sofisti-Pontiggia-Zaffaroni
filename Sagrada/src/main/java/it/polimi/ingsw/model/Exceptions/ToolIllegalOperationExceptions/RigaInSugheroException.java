package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class RigaInSugheroException extends ToolIllegalOperationException {
    private static final String msg = "18";
    public RigaInSugheroException() {
        super(msg);
    }
    public RigaInSugheroException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
