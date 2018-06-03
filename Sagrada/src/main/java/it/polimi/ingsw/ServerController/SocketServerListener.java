package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.Network.SocketController;
import it.polimi.ingsw.Network.SocketMessageClass;
import it.polimi.ingsw.Network.SocketStringHandler;

import java.io.*;
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
        this.os = new ObjectOutputStream(socket.getOutputStream());
        this.is = new ObjectInputStream(socket.getInputStream());
        this.client  = new SocketClient(this);


    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        //convenzione usata:
        // 1: sto ricevendo la risposta  ad una richiesta di un metodo VOID, che è andata a buon fine,
        // 0: sto ricevendo la risposta ad una richiesta, che è fallita, quindi gestisco il relativo errore
        int i = 0;
        int h =0;
        while (i == 0) {
            try{
                SocketMessageClass message = (SocketMessageClass) is.readObject();
                int messagecodex = message.getMessagecodex();
                System.out.println(messagecodex);
                if (messagecodex == 1) {
                    executor.submit(new SocketStringHandlerServer(this.client, this, "OK"));
                }
                if (messagecodex == 0) {
                    executor.submit(new SocketStringHandlerServer(this.client, this, message.getErrorMessage()));
                }
                if(messagecodex == 44){
                    executor.submit(new SocketMessageHandlerServer(message,this.controller,this));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }




    public synchronized void sendMessage(SocketMessageClass message)throws IOException{
        os.writeObject(message);
        os.flush();
        System.out.println("ho inviato il dado");
    }

}
