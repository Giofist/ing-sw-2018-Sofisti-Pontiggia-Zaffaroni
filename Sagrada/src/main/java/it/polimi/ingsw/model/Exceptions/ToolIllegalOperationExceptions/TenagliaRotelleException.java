package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class TenagliaRotelleException extends ToolIllegalOperationException {
    private static final String msg = "21";
    public TenagliaRotelleException() {
        super(msg);
    }
    public TenagliaRotelleException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }

}
