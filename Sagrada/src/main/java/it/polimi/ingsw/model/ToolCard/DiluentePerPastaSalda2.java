package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Player;

public class DiluentePerPastaSalda2 implements ToolAction {
    final static int ID = -1;
    final static String cardTitle = "Diluente per Pasta Salda";
    final static String description = "Parte Seconda.\n" +
            "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";



    @Override

    public void execute (){

    }

    @Override
    public int getID(){
        return ID;
    }
    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String getCardTitle() {
        return cardTitle;
    }
}
