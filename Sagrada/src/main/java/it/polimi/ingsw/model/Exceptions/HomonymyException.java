package it.polimi.ingsw.model.Exceptions;

//questa classe eccezione gestisce il caso in cui l'utente sceglie un nickname non disponibile:
//nel client handler l'errore viene gestito.

// questa stessa classe viene usata nel caso in cui l'utente voglia creare una partita
// con il nome di una partita attiva già presente

public class HomonymyException extends Exception {
    private static String msg = "Questo nome è già utilizzzato da un altro utente";
    public HomonymyException(){
        super(msg);
    }

    public  String getMsg() {
        return msg;
    }
}
