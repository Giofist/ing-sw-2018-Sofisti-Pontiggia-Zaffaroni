package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class DecreaseNotAllowedException extends ToolIllegalOperationException {
    private static final String msg = "Decrease not allowed";
    public DecreaseNotAllowedException() {
        super(msg);
    }
}
