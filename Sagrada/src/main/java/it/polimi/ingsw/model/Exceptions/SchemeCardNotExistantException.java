package it.polimi.ingsw.model.Exceptions;

import it.polimi.ingsw.model.PlayerPackage.Player;

public class SchemeCardNotExistantException extends Exception {
    private static final String msg = "41";
    public SchemeCardNotExistantException(Player player) {
        super(msg + player.getAssociatedUser().getName());
    }
    public SchemeCardNotExistantException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
