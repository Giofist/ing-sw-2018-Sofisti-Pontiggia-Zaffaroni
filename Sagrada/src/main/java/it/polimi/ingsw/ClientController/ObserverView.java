package it.polimi.ingsw.ClientController;

import java.rmi.Remote;

public interface ObserverView extends Remote {
    public void update();

}
