package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An object of this class is continuously running in a thread on the server ad waits for messages exchanged with the client
 * The convention used for the messages id is:
 *  1: The client sent me a response with everything ok
 *  0: The client sent me a response notifying an error, I have to handle it
 *  44: Request from the client
 */
public class SocketServerListener implements Runnable{
    private ClientHandler controller;
    private SocketClient client;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    /**
     * @param socket
     * @param controller
     * @throws IOException
     */
    public SocketServerListener(Socket socket, ClientHandler controller) throws IOException {
        this.controller = controller;
        this.os = new ObjectOutputStream(socket.getOutputStream());
        this.is = new ObjectInputStream(socket.getInputStream());
        this.client  = new SocketClient(this);
    }


    /**
     * @return The client that performed the request
     */
    public SocketClient getClient() {
        return client;
    }


    /**
     * Method responsible for listening to messages and processing correctly the response by creating a new SocketMessageHandlerServer
     * if the client is sending a request to the server, or by creating a new SocketResponseHandlerServer if the server was the first to
     * contact the client and we receive a response from it
     */
    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        // Message type convention:
        // 1: The client sent me a response with everything ok
        // 0: The client sent me a response notifying an error, I have to handle it
        //44: Request from the client
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

            }
        }
    }


    /**
     * This method sends a message to the client
     * @param message The massage object that I want to send
     * @throws IOException Exception thrown if there is any problem in sending the message
     */
    public synchronized void sendMessage(ServerMessage message)throws IOException{
        os.writeObject(message);
        os.flush();
    }

}
