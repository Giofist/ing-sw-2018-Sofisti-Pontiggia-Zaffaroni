package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class PinzaSgrossatriceException extends ToolIllegalOperationException {
    private static final String msg = "17";
    public PinzaSgrossatriceException() {
        super(msg);
    }
    public PinzaSgrossatriceException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
