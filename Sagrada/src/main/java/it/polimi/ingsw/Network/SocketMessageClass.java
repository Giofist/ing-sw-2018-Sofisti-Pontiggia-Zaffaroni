package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.Serializable;

public class SocketMessageClass implements Serializable{
    String clientname;
    ToolRequestClass requestClass;
    // questa classe dovrà essere popolata a seconda delle richieste

    //constructor
    public SocketMessageClass(String clientname){
        this.clientname = clientname;
    }

    public void setRequestClass(ToolRequestClass requestClass){
        this.requestClass = requestClass;
    }



}
