package it.polimi.ingsw.model.Exceptions;

public class IsAlreadyActiveException extends Exception{
    private static final String msg = "30";

        public IsAlreadyActiveException() {
            super(msg);
        }

        public static String getMsg() {
            return msg;
        }
}
