package it.polimi.ingsw.ServerController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TurnController extends UnicastRemoteObject implements Runnable, TurnControllerInterface{


    //constructor
    //dobbiamo mettercelo per forza per la remoteException
    public TurnController() throws RemoteException{ };

    //SOCKET part
    @Override
    public void run(){

    }


    //RMI part
}
