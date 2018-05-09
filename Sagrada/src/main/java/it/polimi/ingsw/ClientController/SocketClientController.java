package it.polimi.ingsw.ClientController;

import java.io.IOException;
import java.net.Socket;

// implemented by pon
public class SocketClientController {
    private Socket socket;

    public SocketClientController(Socket socket){
        this.socket = socket;
    }
    public void run() throws IOException {
        //NB: utilizzo run MA non si tratta di thread
        //comunica lungo il socket
    }
}
