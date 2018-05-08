package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class IncreaseNotAllowedException extends ToolIllegalOperationException {
    private static final String msg = "Increase not allowed";
    public IncreaseNotAllowedException(String msg){
        super(msg);
    }
    public IncreaseNotAllowedException(){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
