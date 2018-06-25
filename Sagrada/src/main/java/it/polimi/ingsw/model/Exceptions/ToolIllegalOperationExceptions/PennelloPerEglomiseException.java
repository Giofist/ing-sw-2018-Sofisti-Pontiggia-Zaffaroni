package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class PennelloPerEglomiseException extends ToolIllegalOperationException {
    private static final String msg = "15";
    public PennelloPerEglomiseException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
