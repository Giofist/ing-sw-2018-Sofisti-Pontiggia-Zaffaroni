package it.polimi.ingsw.model;

public class DiluenteperPastaSalda  implements ToolAction  {
    final static int ID = 11;
    final static String cardTitle = "Diluente per Pasta Salda";
    final static String description = "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal sacchetto.\n" +
                                      "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";
    private Player player;
    private int operation, row, column, diceIntensity;
    DiceColor diceColour;

    public DiluenteperPastaSalda(Player player, int row, int column, int diceIntensity, DiceColor diceColour){
        this.player = player;
        this.row = row;
        this.column =column;
        this.diceIntensity = diceIntensity;
        this.diceColour = diceColour;
    }

    @Override

    public void execute ()throws IllegalOperationException {

        try {
            player.getGametable().getDicepool().addDice(player.getScheme().getTile(row, column).removeDice());
            //player.getGametable().getSpecificDice(diceColour).setIntensity(diceIntensity);/// aggiungere i metodi get specific dice //DA FINIRE
        } catch (IllegalOperationException e) {
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
