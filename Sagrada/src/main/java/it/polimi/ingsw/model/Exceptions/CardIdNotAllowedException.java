package it.polimi.ingsw.model.Exceptions;

public class CardIdNotAllowedException extends Exception {
    private static final String msg = "Non puoi selezionare una carta che non hai estratto, mi spiace\n";
    public CardIdNotAllowedException() {
        super(msg);
    }
    public CardIdNotAllowedException(String msg){
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
