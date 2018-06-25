package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;


// this is to deal with unvalid operation with toolcards, mybe it shoulb be extended for any toolcard
//to show more specific messages.
//what if there are toolcards with more than one invalid operation?
public class ToolIllegalOperationException extends Exception {
    private static final String msg = "22";
    public ToolIllegalOperationException() {
        super(msg);
    }
    public ToolIllegalOperationException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }

}
