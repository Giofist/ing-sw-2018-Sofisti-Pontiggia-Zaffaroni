package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.ClientView.ItalianTranslator;
import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.Translator;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This Class implements the singleton pattern and contains all the information to recognize the client, and the controller
 * for invoking methods from the server
 */
public class ObserverGUI extends UnicastRemoteObject implements Observer {
    private transient AbstractController controller;
    private transient ClientHandlerInterface serverController;
    private transient static ObserverGUI instance;
    private transient String username;
    private transient Translator translator = new ItalianTranslator();

    public  ClientHandlerInterface getServerController() {
        return serverController;
    }

    /**
     * @param controller The server controller with all the callable methods
     */
    public  void setServerController(ClientHandlerInterface controller) {
        serverController = controller;
    }

    private  ObserverGUI() throws RemoteException{};

    /**
     * @return A translator object
     */
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

    /**
     * @return The username of the connected account
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username of the connected account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for updating the client controller
     * @param o
     * @param arg
     * @throws RemoteException
     */
    @Override
    public synchronized void update(Observable o, Object arg) throws RemoteException {
        this.controller.update(o.getState());
    }

    @Override
    public void ping() throws RemoteException {
        return;
    }

}


