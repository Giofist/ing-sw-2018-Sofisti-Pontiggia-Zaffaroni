package it.polimi.ingsw.Network;

public class SocketMessageHandler implements Runnable {
    @Override
    public void run() {

    }
}
/*package it.polimi.ingsw.Network;

        import java.rmi.RemoteException;

public class SocketMessageHandler implements Runnable {
    @Override
    public void run() {
        String message;
        switch (message){

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
}


*/