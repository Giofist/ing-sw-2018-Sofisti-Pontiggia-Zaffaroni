package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaManualeException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

import java.util.List;

//dA TERMINARE
public class TaglierinaManuale2 implements ToolAction{
    private int cost=0;
    final static int ID = 0;
    final static String cardTitle = "Taglierina Manuale: gestione del secondo dado";
    final static String description = "Muovi fino a due dadi dello stesso colore  di un solo dado sul Tracciato dei Round.\n" +
            "Devi rispettare tutte le restrizioni di piazzamento.";

    int oldRow1;
    int oldColumn1;
    int newRow1;
    int newColumn1;
    Dice removedDice;


    public TaglierinaManuale2( int oldRow1, int oldColumn1,  int newRow1, int newColumn1){
        cost=0;
        this.oldRow1 = oldRow1;
        this.oldColumn1 = oldColumn1;
        this.newRow1 = newRow1;
        this.newRow1 = newRow1;
        this.newColumn1 = newColumn1;
    }

    @Override

    public void execute(Player player) throws ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(oldRow1,oldColumn1);
            DiceColor coloritmustbe = player.getColorConstrainForTaglierinaManuale();
            if (removedDice.getColor() == coloritmustbe){
                player.getScheme().removeDice(oldRow1,oldColumn1);
                player.getScheme().setDice(removedDice, newRow1,newColumn1,false,false,false);
            }else{
                throw new TaglierinaManualeException("Il colore non corrisponde a quello del primo dado\n");
            }
        }catch (OutOfMatrixException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (DiceNotExistantException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (TileConstrainException e){
            try{
                player.getScheme().setDice(removedDice, oldRow1,oldColumn1,false,false,false);
            }catch (Exception ecpt){ }
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (SchemeCardNotExistantException e){

        }
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

    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }
}
