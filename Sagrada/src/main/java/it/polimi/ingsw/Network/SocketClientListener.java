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

public class SocketClientListener implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private ObserverView observerView;
    private SocketController controller;

    public SocketClientListener(String ipAddr) throws IOException{
        Socket socket = new Socket(ipAddr, 1337);
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());

        is = new ObjectInputStream(socket.getInputStream());
        os = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Connessione stabilita!\n");
    }

    public void setController(SocketController controller){
        this.controller = controller;
    }


    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        //convenzione usata:
        // 1: sto ricevendo la risposta  ad una richiesta di un metodo VOID, che è andata a buon fine,
        // 0: sto ricevendo la risposta ad una richiesta, che è fallita, quindi gestisco il relativo errore
        // 33: sto ricevendo una risposta positiva ad una richiesta di un metodo NON VOID, quindi devo inoltrare la String che ho ricevuto
        int i=0;
        System.out.println("sono arrivato al run del listener");
        while (i==0){
            if (in.hasNextInt()) {
                int messagecodex = in.nextInt();
                System.out.println(messagecodex);
                if (messagecodex ==1) {
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, "OK" ,true));
                }
                if(messagecodex==0){
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, in.nextLine(),true));
                }
                if (messagecodex == 33){
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, in.nextLine(),false));

                }
            }
            //utilizzo invece una classe per gli update
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
