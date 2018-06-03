package it.polimi.ingsw.NetworkClient;

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
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());

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
        int i = 0;
        System.out.println("sono arrivato al run del listener");
        while (i == 0) {
            try {
                SocketMessageClass message = (SocketMessageClass) is.readObject();
                System.out.println("ho ricevuto un oggetto");
                int messagecodex = message.getMessagecodex();
                if (messagecodex == 1) {
                    System.out.println("ottimo");
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, "OK", true));
                }
                if (messagecodex == 0) {
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, message.getErrorMessage(), true));
                }
                if (messagecodex == 33) {
                    executor.submit(new SocketStringHandler(this.controller, this.observerView, this, message.getAnswermessage(), false));

                }
                if(messagecodex == 44){
                    executor.submit(new SocketMessageHandler(this.controller, this.observerView, this, message, false));

                }
            } catch (Exception e) {

            }
        }
    }

    public synchronized void sendString(String string) {
    out.println(string);
    out.flush();
    }
    public synchronized void sendMessage (SocketMessageClass message)throws IOException{
        os.writeObject(message);
        os.flush();
    }

}
