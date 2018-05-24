package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class AlesatorePerLaminadiRameException extends ToolIllegalOperationException {
    private static final String MSG = "Errore in alesatore per lamina di rame";
    public AlesatorePerLaminadiRameException() {
        super(MSG);
    }
    public AlesatorePerLaminadiRameException(String msg){ super(msg); }
    public static String getMsg() {
        return MSG;
    }
}
