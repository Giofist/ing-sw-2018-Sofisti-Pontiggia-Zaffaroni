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
public class TaglierinaManuale2 extends ToolAction{

    public TaglierinaManuale2(){
        this.cost = 1;
        this.ID = 122;
        this.cardTitle = "Taglierina Manuale: gestione del secondo dado";
        this.description = "Muovi fino a due dadi dello stesso colore  di un solo dado sul Tracciato dei Round.\n" +
                "Devi rispettare tutte le restrizioni di piazzamento.";
    }

    Dice removedDice;


    @Override

    public void execute(Player player, RequestClass requestClass) throws ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(requestClass.getOldRow1(),requestClass.getOldColumn1());
            DiceColor coloritmustbe = player.getColorConstrainForTaglierinaManuale();
            if (removedDice.getColor() == coloritmustbe){
                player.getScheme().removeDice(requestClass.getOldRow1(),requestClass.getOldColumn1());
                player.getScheme().setDice(removedDice, requestClass.getNewRow1(),requestClass.getNewColumn1(),false,false,false);
            }else{
                throw new TaglierinaManualeException("Il colore non corrisponde a quello del primo dado\n");
            }
        }catch (OutOfMatrixException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (DiceNotExistantException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (TileConstrainException e){
            try{
                player.getScheme().setDice(removedDice,requestClass.getOldRow1(),requestClass.getOldColumn1(),false,false,false);
            }catch (Exception ecpt){ }
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (SchemeCardNotExistantException e){

        }
    }


}
