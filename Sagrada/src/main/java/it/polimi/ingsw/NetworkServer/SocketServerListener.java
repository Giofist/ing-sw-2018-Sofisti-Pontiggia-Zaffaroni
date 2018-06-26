package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerListener implements Runnable{
    private ClientHandler controller;
    private SocketClient client;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public SocketServerListener(Socket socket, ClientHandler controller) throws IOException {
        this.controller = controller;
        this.os = new ObjectOutputStream(socket.getOutputStream());
        this.is = new ObjectInputStream(socket.getInputStream());
        this.client  = new SocketClient(this);
    }

    public SocketClient getClient() {
        return client;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        //convenzione usata:
        // 1: sto ricevendo la risposta  ad una richiesta di un metodo VOID, che è andata a buon fine,
        // 0: sto ricevendo la risposta ad una richiesta, che è fallita, quindi gestisco il relativo errore
        //44: sto ricevendo uan richiesta
        int i = 0;
        while (i == 0) {
            try {
                ClientMessage message = (ClientMessage) is.readObject();
                int messagecodex = message.getMessagecodex();
                if (messagecodex == 44) {
                    executor.submit(new SocketMessageHandlerServer(message, this.controller, this));
                } else {
                    executor.submit(new SocketResponseHandlerServer(this.client, message.getErrorMessage(), messagecodex));

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }




    public synchronized void sendMessage(ServerMessage message)throws IOException{
        os.writeObject(message);
        os.flush();
    }

}
