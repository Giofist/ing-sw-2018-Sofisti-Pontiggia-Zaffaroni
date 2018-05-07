package it.polimi.ingsw.ServerController;

import java.rmi.Remote;
//l'interfaccia che sarà condivisa con il client
//viene caricata sul RMI register ecc...
//guardate le slide
public interface RMIRemoteServerClientHandler extends Remote {
    //here all the methods I want to invoke from the client Controller


}
