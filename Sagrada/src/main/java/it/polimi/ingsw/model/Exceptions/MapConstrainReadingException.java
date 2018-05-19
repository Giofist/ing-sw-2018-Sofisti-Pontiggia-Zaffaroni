package it.polimi.ingsw.model.Exceptions;


// questa classe segnala anche quale mappa non rispetta le convenzioni di lettura che il programma usa per leggerla e quindi è corrotta per qualsiasi motivo
// banalmente, sta scritto un numero al posto di una lettera ecc....
public class MapConstrainReadingException extends Exception {
    private static final String msg = "Unable to read the map from the file Map.txt: id map ";

    public MapConstrainReadingException(int idmap) {
        super(msg + idmap);
    }

    public MapConstrainReadingException(String msg) {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }


    ////
}