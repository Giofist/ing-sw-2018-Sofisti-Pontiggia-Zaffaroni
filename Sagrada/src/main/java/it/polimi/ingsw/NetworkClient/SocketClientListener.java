package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.NetworkServer.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClientListener implements Runnable {
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private Observer observerView;
    private ClientHandlerInterface controller;

    public SocketClientListener(Socket socket) throws IOException {
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
    }

    public void setController(ClientHandlerInterface controller, Observer observer) {
        this.controller = controller;
        this.observerView = observer;
    }


    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        //convenzione usata:
        // 1: sto ricevendo la risposta  ad una richiesta di un metodo, che è andata a buon fine(può essere void o non void).
        // 0: sto ricevendo la risposta ad una richiesta, che è fallita, quindi gestisco il relativo errore
        //44: sto ricevendo una richiesta(che dal server può essere solo un'update o un ping)
        int i = 0;
        while (i == 0) {
            try{
                ServerMessage message = (ServerMessage) is.readObject();
                int messagecodex = message.getMessagecodex();
                if (messagecodex == 44) {
                    executor.submit(new SocketMessageHandler(this.observerView, this, message));
                } else {
                    executor.submit(new SocketResponseHandler(this.controller, message.getMessage(), messagecodex, message.getList()));
                }
            }catch(Exception e){
                // do something?
            }

        }
    }

    public synchronized void sendMessage(ClientMessage message) throws IOException {
        os.writeObject(message);
        os.flush();
    }

}
