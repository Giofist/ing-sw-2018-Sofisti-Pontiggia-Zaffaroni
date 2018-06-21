package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.NetworkServer.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClientListener implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private Observer observerView;
    private SocketController controller;

    public SocketClientListener(String ipAddr) throws IOException{
        Socket socket = new Socket(ipAddr, 1337);
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
    }

    public void setController(SocketController controller, Observer observer){
        this.controller = controller;
        this.observerView = observer;
    }


    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        //convenzione usata:
        // 1: sto ricevendo la risposta  ad una richiesta di un metodo, che è andata a buon fine.
        // 0: sto ricevendo la risposta ad una richiesta, che è fallita, quindi gestisco il relativo errore
        //44: sto ricevendo una richiesta(che dal server può essere solo un'update)
        int i = 0;
        while (i == 0) {
            try {
                ServerMessage message = (ServerMessage) is.readObject();
                int messagecodex = message.getMessagecodex();
                if(messagecodex == 44){
                    executor.submit(new SocketMessageHandler( this.observerView, this, message));
                }else{
                    executor.submit(new SocketResponseHandler(this.controller, message.getMessage(), messagecodex,message.getList()));
                }
                message = null;
            } catch (Exception e) {

            }
        }
    }

    public synchronized void sendMessage (ClientMessage message)throws IOException{
        os.writeObject(message);
        os.flush();
    }

}
