package it.polimi.ingsw.model.Exceptions;

public class IsAlreadyActiveException extends Exception{
    private static final String msg = "Sei già attivo su un altro dispositivo!";

        public IsAlreadyActiveException() {
            super(msg);
        }

        public IsAlreadyActiveException(String msg) {
            super(msg);
        }

        public static String getMsg() {
            return msg;
        }
}
