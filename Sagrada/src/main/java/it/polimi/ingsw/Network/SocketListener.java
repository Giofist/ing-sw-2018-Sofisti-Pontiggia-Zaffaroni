package it.polimi.ingsw.Network;

import it.polimi.ingsw.ClientView.ObserverView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketListener implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private SocketStringHandler messageHandler;
    private ObserverView observerView;
    private SocketController controller;

    public SocketListener(String ipAddr, SocketController controller, SocketStringHandler messageHandler) throws IOException{
        Socket socket = new Socket(ipAddr, 1337);
        System.out.println("Connessione stabilita!\n");
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        this.is = new ObjectInputStream(socket.getInputStream());
        this.os = new ObjectOutputStream(socket.getOutputStream());
        this.controller=controller;
        this.messageHandler = messageHandler;


    }


    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        int i=0;
        while (i==0){
            if (in.hasNextInt()) {
                if (in.nextInt() == 1) {
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, "OK" ));
                }
                if(in.nextInt()==0){
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, in.nextLine()));
                }
            }
            try{
                SocketMessageClass message =(SocketMessageClass)is.readObject();
                executor.submit(new SocketMessageHandler());

            }catch (IOException e){
                //do something
            }catch (ClassNotFoundException e){
                //do something
            }
        }


    }

    public synchronized void sendString(String string) {
    out.println(string);
    out.flush();
    }
    public synchronized void sendMessage (SocketMessageClass message)throws IOException{
        os.writeObject(message);
        out.flush();
    }

}
