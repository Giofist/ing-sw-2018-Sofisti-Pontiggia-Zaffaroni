package it.polimi.ingsw.model;

public class NotEnoughSegnaliniException extends Exception{
    private static final String msg = "not enough segnalini";
    public NotEnoughSegnaliniException() {
        super(msg);
    }
}
