package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TenagliaRotelleException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

//scusate questa non l'ho capita
public class TenagliaRotelle  implements ToolAction {
    final static int ID = 8;
    final static String cardTitle = "Tenaglia a Rotelle";
    final static String description = "Dopo il tuo primo turno scegli immediatamente un altro dado.\n" +
                                      "Salta il secondo turno di questo round.";

    private int row;
    private int column;
    private int selectedDiceIndex;

    public TenagliaRotelle(int selectedDiceIndex, int row, int column){
        this.selectedDiceIndex = selectedDiceIndex;
        this.column = column;
        this.row = row;


    }

    @Override

    public void execute (Player player) throws ToolIllegalOperationException{
        if(player.getNumberoftimesyouhaveplayedthisround()==2){
            throw new TenagliaRotelleException("sei al secondo turno: non puoi usare questa carta al secondo turno!");
        }
        try{
            player.getScheme().setDice(  player.getGametable().getRoundDicepool().getDice(selectedDiceIndex), row, column, false,false,false  );
            player.setMustpassTurn(true);
        }catch(TileConstrainException e){
            throw new TenagliaRotelleException(TenagliaRotelleException.getMsg()+e.getMessage());
        }catch(OutOfMatrixException e){
            throw new TenagliaRotelleException(TenagliaRotelleException.getMsg()+e.getMessage());
        }catch (SchemeCardNotExistantException e){

        }

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
