package it.polimi.ingsw.model.Exceptions;


// questa classe segnala anche quale mappa non rispetta le convenzioni di lettura che il programma usa per leggerla e quindi è corrotta per qualsiasi motivo
// banalmente, sta scritto un numero al posto di una lettera ecc....
public class MapConstrainReadingException extends Exception {
    private static final String msg = "32";

    public MapConstrainReadingException() {
        super(msg);
    }
    public static String getMsg() {
        return msg;
    }
}