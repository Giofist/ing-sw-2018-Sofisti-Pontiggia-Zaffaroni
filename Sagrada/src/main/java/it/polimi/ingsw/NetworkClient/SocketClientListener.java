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

/**
 * An object of this class is continuously running in a thread on the client ad waits for messages exchanged with the server
 * Messages id conventions:
 *  1: This message means everything went fine in a client request(it can be void or not).
 *  0: This message means something went wrong in the requesto to the server and we have to handle the error properly
 *  44: This message means the server is contacting the client (it can be either a ping or an update)
 */
public class SocketClientListener implements Runnable {
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private Observer observerView;
    private SocketController controller;

    /**
     * This constructor saves the streams for the input and output communication with the server
     * @param socket Socket for the communication
     * @throws IOException Exception thrown when there is some problem in saving the streams for the communication
     */
    public SocketClientListener(Socket socket) throws IOException {
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * This method sets the interface exposed by the server with all the methods that we can call and the view which is actually running on client
     * @param controller Server controller interface
     * @param observer
     */
    public void setController(SocketController controller, Observer observer) {
        this.controller = controller;
        this.observerView = observer;
    }


    /**
     * Method responsible for listening to messages and processing correctly the response by creating a new SocketMessageHandler
     * if the server is contacting the client, or by creating a new SocketResponseHandler if the client was the first to
     * contact the server and we receive a response from it
     */
    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        // Messages id conventions:
        // 1: This message means everything went fine in a client request(it can be void or not).
        // 0: This message means something went wrong in the requesto to the server and we have to handle the error properly
        //44: This message means the server is contacting the client (it can be either a ping or an update)
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

    /**
     * This method sends a message to the server
     * @param message A message objects containing a request
     * @throws IOException Exception thrown if there is any problem in sending the message
     */
    public synchronized void sendMessage(ClientMessage message) throws IOException {
        os.writeObject(message);
        os.flush();
    }

}
