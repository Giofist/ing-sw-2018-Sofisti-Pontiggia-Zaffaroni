package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

public class SocketMessageClass {
    String clientname;
    ToolRequestClass requestClass;

    //constructor
    public SocketMessageClass(String clientname){
        this.clientname = clientname;
    }

    public void setRequestClass(ToolRequestClass requestClass){
        this.requestClass = requestClass;
    }



}
