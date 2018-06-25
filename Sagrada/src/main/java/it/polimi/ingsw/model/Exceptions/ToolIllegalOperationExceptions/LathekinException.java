package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class LathekinException extends ToolIllegalOperationException {
    private static final String msg = "13";
    public LathekinException(){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
