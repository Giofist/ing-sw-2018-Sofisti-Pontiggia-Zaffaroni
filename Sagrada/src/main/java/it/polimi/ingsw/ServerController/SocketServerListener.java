package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.Network.SocketController;
import it.polimi.ingsw.Network.SocketMessageClass;
import it.polimi.ingsw.Network.SocketStringHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerListener implements Runnable{
    private Socket socket;
    private ClientHandler controller;
    private SocketClient client;
    Scanner in;
    PrintWriter out;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public SocketServerListener(Socket socket, ClientHandler controller) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream());
        this.controller = controller;
        this.is = new ObjectInputStream(socket.getInputStream());
        this.os = new ObjectOutputStream(socket.getOutputStream());
        this.client  = new SocketClient(this);


    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        //convenzione usata:
        // 1: sto ricevendo la risposta  ad una richiesta di un metodo VOID, che è andata a buon fine,
        // 0: sto ricevendo la risposta ad una richiesta, che è fallita, quindi gestisco il relativo errore
        int i = 0;
        while (i == 0) {
            if (in.hasNextInt()) {
                int messagecodex = in.nextInt();
                System.out.println(messagecodex);
                if (messagecodex == 1) {
                    executor.submit(new SocketStringHandlerServer(this.client, this, "OK"));
                }
                if (messagecodex == 0) {
                    executor.submit(new SocketStringHandlerServer(this.client, this, in.nextLine()));
                }

            }
            try {
                SocketMessageClass message = (SocketMessageClass) is.readObject();
                System.out.println(message.getMethodtoinvoke());
                System.out.println("forza roma");
                executor.submit(new SocketMessageHandlerServer(message,this.controller,this));

            } catch (IOException e) {
                //do something
            } catch (ClassNotFoundException e) {
                //do something
            }

        }
    }

    public void sendString(int messagecodex, String message){
        if(message == null){
            out.println(messagecodex);
        }else{
            out.println(messagecodex + " " + message);
        }
        out.flush();
    }

    public synchronized void sendMessage(SocketMessageClass message)throws IOException{
        os.writeObject(message);
        out.flush();
    }

}
