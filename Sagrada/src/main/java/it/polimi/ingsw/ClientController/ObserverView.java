package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.Remote;

public interface ObserverView extends Remote {
    public void update();
    public void showErrorMessage(String message);
    public void showSchemeCards(SchemeCard scheme, SchemeCard card);

}
