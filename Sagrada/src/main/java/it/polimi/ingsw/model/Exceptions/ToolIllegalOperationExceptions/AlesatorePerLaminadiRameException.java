package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class AlesatorePerLaminadiRameException extends ToolIllegalOperationException {
    private static final String MSG = "11";
    public AlesatorePerLaminadiRameException() {
        super(MSG);
    }
    public static String getMsg() {
        return MSG;
    }
}
