package it.polimi.ingsw.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketListener implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private SocketMessageHandler messageHandler;

    private SocketController socketClientController;
    public SocketListener(String ipAddr, SocketController socketClientController, SocketMessageHandler messageHandler) throws IOException{
        Socket socket = new Socket(ipAddr, 1337);
        System.out.println("Connessione stabilita!\n");
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        this.is = new ObjectInputStream(socket.getInputStream());
        this.os = new ObjectOutputStream(socket.getOutputStream());
        this.socketClientController=socketClientController;
        this.messageHandler = messageHandler;


    }


    @Override
    public void run() {
        int i=0;
        while (i==0){
            if (in.hasNextInt()){
                if(in.nextInt() == '1'){

                }
                messageHandler.handleString(in.nextLine());
            }
            try{
                SocketMessageClass message =(SocketMessageClass)is.readObject();
                messageHandler.handleMessage(message);

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
