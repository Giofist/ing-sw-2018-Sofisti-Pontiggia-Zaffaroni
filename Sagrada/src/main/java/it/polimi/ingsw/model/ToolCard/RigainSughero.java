package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.RigaInSugheroException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionata by pon
public class RigainSughero  implements ToolAction {
    final static int ID = 9;
    final static String cardTitle = "Riga in Sughero";
    final static String description = "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado.\n" +
                                      "Devi rispettare tutte le restrizioni di piazzamento.";

    private int selectedDiceIndex;
    private int operation;
    private int row;
    private int column;
    private Dice dice;

    public RigainSughero( int row, int column, int selectedDiceIndex){
        this.selectedDiceIndex = selectedDiceIndex;
        this.row = row;
        this.column =column;
    }

    @Override

    public void execute (Player player) throws ToolIllegalOperationException {
        //ricordarsi di fare get and remove dei dadi, non dimenticare la remove
        try {
            dice = player.getGametable().getRoundDicepool().getDice(selectedDiceIndex);
            player.getGametable().getRoundDicepool().removeDice(selectedDiceIndex);
            boolean thereisadicenearyou = player.getScheme().ThereisaDicenearYou(this.row, this.column);
            if (thereisadicenearyou) {
                throw new RigaInSugheroException("Non puoi mettere un dado se ce n'è uno vicino!\n");
            } else player.getScheme().setDice(dice, row, column, false, false, true);

        }catch (Exception e){
            try{
                player.getGametable().getRoundDicepool().addDice(selectedDiceIndex,dice);
            }catch(Exception er){
                //do nothing
            }
            throw new RigaInSugheroException(RigaInSugheroException.getMsg()+ e.getMessage());
        }


        //not implemented yet
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
