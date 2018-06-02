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
    private ObserverView observerView;
    private SocketController controller;

    public SocketListener(String ipAddr) throws IOException{
        Socket socket = new Socket(ipAddr, 1337);
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());

        //this.is = new ObjectInputStream(socket.getInputStream());

        //this.os = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Connessione stabilita!\n");


    }

    public void setController(SocketController controller){
        this.controller = controller;
    }


    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        int i=0;
        System.out.println("sono arrivato al run del listener");
        while (i==0){
            if (in.hasNextInt()) {
                int h = in.nextInt();
                System.out.println(h);
                if (h ==1) {
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, "OK" ,true));
                }
                if(h==0){
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, in.nextLine(),true));
                }
                if (h == 33){
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, in.nextLine(),false));

                }
            }
            /*try{
                SocketMessageClass message =(SocketMessageClass)is.readObject();
                executor.submit(new SocketMessageHandler());

            }catch (IOException e){
                //do something
            }catch (ClassNotFoundException e){
                //do something
            }*/
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
