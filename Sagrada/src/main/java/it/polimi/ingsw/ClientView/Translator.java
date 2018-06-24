package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.Player;

public interface  Translator {
    String translateMatch(Match match);
    String translatePlayer(Player player);
    String translateException(String exceptioncode)
}
