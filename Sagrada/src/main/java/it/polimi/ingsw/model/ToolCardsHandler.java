package it.polimi.ingsw.model;
import java.util.*;

public class ToolCardsHandler {
    private  List<ToolAction> toolActionDeck = new LinkedList<ToolAction>(); // il deck delle azioni che il giocatore invoca
    private Player player;
    private int[] costs; // un'array che tiene memoria dei costi, 1 o 2, di ciascun utensile


    // il costruttore
    public ToolCardsHandler(Player player){
        this.player = player;
        this.costs = new int[12];
    }


    public void addAction(ToolAction action){
        toolActionDeck.add(action);
    }

    //command design pattern
    public void doAction() {
        for (ToolAction action :
             toolActionDeck)
            action.execute();
    }

    public List<ToolAction> getToolActionDeck() {
        return toolActionDeck;
    }
    public void clearDeck(){
        this.toolActionDeck.clear();
    }




}
