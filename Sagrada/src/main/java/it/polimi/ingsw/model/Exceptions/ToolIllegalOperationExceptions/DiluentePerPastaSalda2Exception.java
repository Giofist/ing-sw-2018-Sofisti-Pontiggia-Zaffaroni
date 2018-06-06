package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class DiluentePerPastaSalda2Exception extends ToolIllegalOperationException{
    private static final String msg = "Errore in diluente per Pasta salda\n";

    public DiluentePerPastaSalda2Exception() {
        super(msg);
    }

    public DiluentePerPastaSalda2Exception(String msg) {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
