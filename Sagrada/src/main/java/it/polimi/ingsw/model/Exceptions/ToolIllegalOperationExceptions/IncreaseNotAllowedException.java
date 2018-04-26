package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class IncreaseNotAllowedException extends ToolIllegalOperationException {
    private static final String msg = "Increase not allowed";
    public IncreaseNotAllowedException(){
        super(msg);
    }
}
