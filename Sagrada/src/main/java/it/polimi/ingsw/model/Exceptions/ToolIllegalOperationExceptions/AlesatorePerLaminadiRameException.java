package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class AlesatorePerLaminadiRameException extends ToolIllegalOperationException {
    private static final String msg = "Errore in alesatore per lamina di rame";
    public AlesatorePerLaminadiRameException() {
        super(msg);
    }
    public AlesatorePerLaminadiRameException(String msg){ super(msg); }
    public static String getMsg() {
        return msg;
    }
}
