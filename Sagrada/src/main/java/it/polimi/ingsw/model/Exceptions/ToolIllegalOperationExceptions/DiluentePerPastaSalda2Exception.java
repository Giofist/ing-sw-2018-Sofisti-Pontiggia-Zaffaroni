package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class DiluentePerPastaSalda2Exception extends ToolIllegalOperationException{
    private static final String msg = "12";

    public DiluentePerPastaSalda2Exception() {
        super(msg);
    }


    public static String getMsg() {
        return msg;
    }
}
