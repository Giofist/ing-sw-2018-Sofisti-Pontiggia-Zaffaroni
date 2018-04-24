package it.polimi.ingsw.Controller;


public class StartServer {
    private static Server server;

    public static void main(String[] args){
        Server server = new Server(1337);
        server.startServer();
    }

}
