package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.ClientView.ItalianTranslator;
import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.Translator;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObserverGUI extends UnicastRemoteObject implements Observer {
    private AbstractController controller;
    private ClientHandlerInterface serverController;
    private static ObserverGUI instance;
    private String username;
    private Translator translator = new ItalianTranslator();
    public  ClientHandlerInterface getServerController() {
        return serverController;
    }
    public  void setServerController(ClientHandlerInterface controller) {
        serverController = controller;
    }

    public ObserverGUI() throws RemoteException{};
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
        State state = o.getState();
        switch (state) {
            case ERRORSTATE: {
                break;
            }
            case HASSETADICESTATE: {
                break;
            }
            case HASUSEDATOOLCARDACTIONSTATE: {
                break;
            }
            case MATCHNOTSTARTEDYETSTATE: {

                break;
            }
            case MUSTPASSTURNSTATE: {
                break;
            }
            case MUSTSSETDILUENTEPERPASTASALDASTATE: {
                break;
            }
            case MUSTSETPENNELLOPERPASTASALDASTATE: {
                break;
            }
            case NOTYOURTURNSTATE: {
                break;
            }
            case STARTTURNSTATE: {
                break;
            }
            case ENDMATCHSTATE: {
                break;
            }
            case MUSTSETSCHEMECARD: {
                break;
            }
        }
    }

}
