package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;

public class DiluentePerPastaSaldaException extends ToolIllegalOperationException {
    private static final String msg = "7";
    public DiluentePerPastaSaldaException() {
        super(msg);
    }
    public DiluentePerPastaSaldaException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
