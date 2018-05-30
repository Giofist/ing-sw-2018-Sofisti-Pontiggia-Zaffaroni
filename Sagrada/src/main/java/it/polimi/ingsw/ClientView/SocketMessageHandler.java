package it.polimi.ingsw.ClientView;

import java.rmi.RemoteException;

public class SocketMessageHandler {
    SocketClientController clientController;
    ObserverView observerView;
    SocketListener listener;
    public SocketMessageHandler(SocketClientController clientController, ObserverView observerView, SocketListener listener){
        this.clientController = clientController;
        this.observerView = observerView;
        this.listener = listener;
    }
    public synchronized void handleString(String message){
        switch(message){
            case "notifyGameisStarting": try{
                System.out.println("ho ricevuto una notifica\n");
                observerView.notifyGameisStarting();
                listener.sendString("1");
            }catch (RemoteException e){
                listener.sendString("0"+e.getMessage());
            }
            break;
            case "update": try{
                observerView.update();
                listener.sendString("1");
            }catch (RemoteException e){
                listener.sendString("0"+e.getMessage());
            }
            break;
            case "showErrorMessage": try{
                observerView.showErrorMessage(message);
                listener.sendString("1");
            }catch (RemoteException e){
                listener.sendString("0"+e.getMessage());
            }
            break;
            default: System.out.println("niente di nuovo sul fronte occipitale");
        }

    }
    public synchronized void handleMessage(SocketMessageClass socketMessageClass){

    }
}
