package it.polimi.ingsw.model;


// this is to deal with unvalid operation with toolcards, mybe it shoulb be extended for any toolcard
//to show more specific messages.
//what if there are toolcards with more than one invalid operation?
public class IllegalOperationException extends Exception {
    private static final String msg = "you can't do that";
    public IllegalOperationException() {
        super(msg);
    }
    public IllegalOperationException(String msg){ super(msg); } //ho preparato il costruttore per l'estensione,
    //ma trovo complesso doverla estendere perchè l'ho scritto abbastanza ovunque
}
