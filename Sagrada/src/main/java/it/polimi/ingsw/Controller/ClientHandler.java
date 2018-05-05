package it.polimi.ingsw.Controller;

import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.NumberOfPlayersNotAllowedException;
import it.polimi.ingsw.model.MultipleUserGameList;
import it.polimi.ingsw.model.User;
import it.polimi.ingsw.model.UsersList;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;


//raga ho fatto una prima implementazione del client handler
public class ClientHandler implements Runnable,Serializable {
    Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

    }
}
