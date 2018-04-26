package it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions;


// this is to deal with unvalid operation with toolcards, mybe it shoulb be extended for any toolcard
//to show more specific messages.
//what if there are toolcards with more than one invalid operation?
public class ToolIllegalOperationException extends Exception {
    private static final String msg = "you can't do that";
    public ToolIllegalOperationException() {
        super(msg);
    }
    public ToolIllegalOperationException(String msg){ super(msg); } //ho preparato il costruttore per l'estensione,
    //ma trovo complesso doverla estendere perchè l'ho scritto abbastanza ovunque
}
