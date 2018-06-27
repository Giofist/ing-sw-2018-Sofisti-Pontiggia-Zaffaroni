package it.polimi.ingsw.model.Exceptions;

import it.polimi.ingsw.model.Player;

public class SchemeCardNotExistantException extends Exception {
    private static final String msg = "41";
    public SchemeCardNotExistantException(Player player) {
        super(msg + player.getName());
    }
    public static String getMsg() {
        return msg;
    }
}
