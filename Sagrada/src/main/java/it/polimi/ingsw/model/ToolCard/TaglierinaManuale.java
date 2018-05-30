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

// DA TERMINARE
public class TaglierinaManuale  extends ToolAction {
    Dice removedDice;
    public TaglierinaManuale(){
        this.cost = 1;
        this.ID =12;
        this.cardTitle = "Taglierina Manuale";
        this.description = "Muovi fino a due dadi dello stesso colore  di un solo dado sul Tracciato dei Round.\n" +
                "Devi rispettare tutte le restrizioni di piazzamento.";
    }

    @Override
    public void execute(Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            List<DiceColor> diceColors = player.getGametable().getRoundTrack().allColors();
            if (diceColors.contains(removedDice.getColor())){
                player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
                player.getScheme().setDice(removedDice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(),false,false,false);
                player.setColorConstrainForTaglierinaManuale(removedDice.getColor());
            }else{
                throw new TaglierinaManualeException("Non c'è nessun dado con lo stesso colore nel Tracciato Round\n");
            }
        }catch (OutOfMatrixException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (DiceNotExistantException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (TileConstrainException e){
            try{
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(),false,false,false);
            }catch (Exception ecpt){ }
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (RoundTrackException e){
            throw new TaglierinaManualeException(TaglierinaManualeException.getMsg()+e.getMessage());
        }catch (SchemeCardNotExistantException e){
            //
        }




    }

}
