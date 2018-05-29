package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class Martelletto  implements ToolAction {
    final static int ID = 7;
    final static String cardTitle = "Martelletto";
    final static String description = "Tira nuovamente tutti i dadi della riserva.\n" +
                                      "Questa carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.";

    private int selectedDiceIndex;
    private int operation;

    public Martelletto( int selectedDiceIndex, int operation){
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public void execute (Player player)throws ToolIllegalOperationException{
        //se non lancia eccezioni ci siamo dimenticati di qualcosa
        for(int i=0; i<player.getGametable().getRoundDicepool().getDicePoolSize();i++)
        player.getGametable().getRoundDicepool().getDice(i).setRandomIntensity();

    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle(){return cardTitle;}

    @Override
    public String getDescription(){
        return description;
    }

}
