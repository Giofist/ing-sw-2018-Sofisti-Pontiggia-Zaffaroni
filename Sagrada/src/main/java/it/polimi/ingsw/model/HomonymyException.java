package it.polimi.ingsw.model;

//questa classe eccezione gestisce il caso in cui l'utente sceglie un nickname non disponibile:
//nel client handler l'errore viene gestito.

// questa stessa classe viene usata nel caso in cui l'utente voglia creare una partita
// con il nome di una partita attiva già presente

public class HomonymyException extends Exception {
    private static String msg = "nome non disponibile";
    public HomonymyException(){
        super(msg);
    }
}
