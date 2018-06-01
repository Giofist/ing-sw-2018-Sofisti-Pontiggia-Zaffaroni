package it.polimi.ingsw.Network;

import it.polimi.ingsw.ClientView.ObserverView;

import java.rmi.RemoteException;

public class SocketMessageHandler implements Runnable{
    SocketController clientController;
    ObserverView observerView;
    SocketListener listener;
    private String message;
    private RemoteException exception;
    public SocketMessageHandler(SocketController clientController, ObserverView observerView, SocketListener listener, String message){
        this.clientController = clientController;
        this.observerView = observerView;
        this.listener = listener;
        this.message = message;
    }
    public synchronized void run(){
        switch(message){
            case "OK":
                clientController.notifyAll();
                break;
                default: this.exception = new RemoteException(message);
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
        }

    }
    public synchronized void check() throws RemoteException{
        if(this.exception == null){
            return;
        }
        throw this.exception;

    }
}
