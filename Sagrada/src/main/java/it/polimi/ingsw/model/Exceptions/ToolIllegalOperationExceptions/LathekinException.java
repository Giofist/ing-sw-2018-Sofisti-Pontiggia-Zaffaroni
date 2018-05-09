package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;

public class LathekinException extends ToolIllegalOperationException {
    private static final String msg = "Errore in Lathekin\n";
    public LathekinException(){
        super(msg);
    }
    public LathekinException(String msg){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
