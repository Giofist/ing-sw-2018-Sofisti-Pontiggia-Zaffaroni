package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class TaglierinaManualeException extends ToolIllegalOperationException {
    private static final String msg = "20";
    public TaglierinaManualeException() {
        super(msg);
    }
    public TaglierinaManualeException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}

