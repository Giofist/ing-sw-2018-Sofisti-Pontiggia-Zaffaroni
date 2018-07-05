package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.ClientView.ItalianTranslator;
import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.Translator;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObserverGUI extends UnicastRemoteObject implements Observer {
    private transient AbstractController controller;
    private transient ClientHandlerInterface serverController;
    private transient static ObserverGUI instance;
    private transient String username;
    private transient Translator translator = new ItalianTranslator();
    private static boolean isYourTurn;

    public static boolean isIsYourTurn() {
        return isYourTurn;
    }

    public static void setIsYourTurn(boolean isYourTurn) {
        ObserverGUI.isYourTurn = isYourTurn;
    }

    public  ClientHandlerInterface getServerController() {
        return serverController;
    }

    public  void setServerController(ClientHandlerInterface controller) {
        serverController = controller;
    }

    private  ObserverGUI() throws RemoteException{};

    public Translator getTranslator() {
        return translator;
    }

    public static ObserverGUI Singleton() {
        if (instance == null) {
            try{
                instance = new ObserverGUI();
            }catch(RemoteException e){
                // do nothing
            }
        }
        return instance;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public synchronized void update(Observable o, Object arg) throws RemoteException {
        this.controller.update(o.getState());
    }

    @Override
    public void ping() throws RemoteException {
        return;
    }

}


