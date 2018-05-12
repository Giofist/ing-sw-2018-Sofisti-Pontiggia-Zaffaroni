package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Player;
//dA TERMINARE
public class TaglierinaManuale2 implements ToolAction{
    final static int ID = 0;
    final static String cardTitle = "Taglierina Manuale: gestione del secondo dado";
    final static String description = "Muovi fino a due dadi dello stesso colore  di un solo dado sul Tracciato dei Round.\n" +
            "Devi rispettare tutte le restrizioni di piazzamento.";

    int oldRow1;
    int oldColumn1;
    int newRow1;
    int newColumn1;

    public TaglierinaManuale2( int oldRow1, int oldColumn1,  int newRow1, int newColumn1){
        this.oldRow1 = oldRow1;
        this.oldColumn1 = oldColumn1;
        this.newRow1 = newRow1;
        this.newRow1 = newRow1;
        this.newColumn1 = newColumn1;
    }

    @Override

    public void execute(Player player) {

/

    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle() {
        return cardTitle;
    }

    @Override
    public String getDescription(){
        return description;
    }
}

}
