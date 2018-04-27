package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo pubblico
public class ColoriDiversiRiga implements GoalCard {
    static int ID = 1;
    static String name = "Colori Diversi - Riga";
    static String description = "Righe senza colori ripetuti.";

    @Override
    public void  calculatepoint(Player player) {
        int[] counter;
        counter = new int[5];  //array contenenete le occorrenze dei colori
        int nRow=0;
        int numColor=0;
        boolean allDiff=true;

        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                if(player.getScheme().getDiceColour(row, column)== DiceColor.RED){
                    counter[0]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.BLUE){
                    counter[1]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.VIOLET){
                    counter[2]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.YELLOW){
                    counter[3]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.GREEN){
                    counter[4]++;
                }
            }
            for(int i=0; i<5 && allDiff; i++){
                if(counter[i]<=1){
                    numColor+=counter[i];
                }
                else allDiff = false;
            }
            if(numColor==5&&allDiff){
                nRow++;
            }
            allDiff=true;
        }
        player.addPoints(nRow*6);
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
