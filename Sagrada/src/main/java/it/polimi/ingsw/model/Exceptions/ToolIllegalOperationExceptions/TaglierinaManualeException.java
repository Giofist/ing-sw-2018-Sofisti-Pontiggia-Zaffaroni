package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class TaglierinaManualeException extends ToolIllegalOperationException {
    private static final String msg = "Erroe in Taglierina Manuale\n";
    public TaglierinaManualeException() {
        super(msg);
    }
    public TaglierinaManualeException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}

