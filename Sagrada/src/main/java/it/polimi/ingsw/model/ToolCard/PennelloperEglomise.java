package it.polimi.ingsw.model.ToolCard;

<<<<<<< HEAD:Sagrada/src/main/java/it/polimi/ingsw/model/PennelloperEglomise.java
public class PennelloperEglomise  implements ToolAction  {
=======
import it.polimi.ingsw.model.Exceptions.IllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PennelloperEglomise  implements ToolAction {
>>>>>>> 1f0a54b0963d48fd5bced796a14de4ef59b5e4c8:Sagrada/src/main/java/it/polimi/ingsw/model/ToolCard/PennelloperEglomise.java
    final static int ID = 2;
    final static String cardTitle = "Pennello per Eglomise";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;

    public PennelloperEglomise(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
    }

    @Override

    public void execute () throws IllegalOperationException {

    try {

    } catch  (IllegalOperationException e){
        throw e;
        }
    }

    @Override
    public int getID(){
        return ID;
    }
    @Override
    public String getDescription(){
        return description;
    }
}
