package it.polimi.ingsw.model.PublicGoalCards;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;
import java.util.LinkedList;


public class DiagonaliColorate implements GoalCard,Serializable {
    static int ID = 9;

    /**
     * @param player A player for whom we want to calculate the points scored in this goal
     */
    @Override
    public void calculatepoint(Player player) {
        try{
            for(Tile tile: player.getScheme()){
                LinkedList<Dice> list = new LinkedList<Dice>();
                findsamecolordices(tile.getRow(),tile.getColumn(),player,list);
                if(list.size() >1){
                    player.addPoints(list.size());
                }
            }
        }catch(SchemeCardNotExistantException e){
            // do nothing
        }
    }


    /**
     * @return The id of the public goal card
     */
    @Override
    public int getID() {
        return ID;
    }


    /**
     * This method, given a position on the player's scheme card where there is a Dice, is going to check if there are
     * other dices of the same color in the tiles which are diagonally near to the position specified. If in any direction
     * a match is found this method will call itself recursively
     * @param row Row position of the central tile
     * @param column Column position of the central tile
     * @param player The player for which we are calculating the score
     * @param list List of same color dices found until now
     */
    private void findsamecolordices(int row, int column,Player player,LinkedList<Dice> list){
        try {
            DiceColor thecolorofthistile = player.getScheme().getDiceColour(row, column);
            list.add(player.getScheme().getDice(row, column));
            player.getScheme().removeDice(row,column);

            // Several try-catch statements to check the color of the dices placed in
            // all the tiles diagonally near to the tile that I'm considering

            try {
                if (player.getScheme().getDiceColour(row + 1, column + 1).equals(thecolorofthistile)) {
                    findsamecolordices(row + 1, column + 1, player, list);
                }
            } catch (Exception e) {
                //here you can get a DiceNotExistantException or an OutOfMatrixException
                //there is no dice, or you're out of the matrix
                //nothing to do, just go ahead in calculating the points
                //in the recursive call there will never be exceptions in thecolorofthistile
                //because I've controlled the existance of the tile and the dice here
            }

            try {
                if (player.getScheme().getDiceColour(row - 1, column - 1).equals(thecolorofthistile)) {
                    findsamecolordices(row - 1, column - 1, player, list);
                }
            }catch (Exception e) {
                //there is no dice, or you're out of the matrix
            }


            try {
                if (player.getScheme().getDiceColour(row - 1, column + 1).equals(thecolorofthistile)) {
                    findsamecolordices(row - 1, column + 1, player, list);
                }
            }catch (Exception e){
                //there is no dice, or you're out of the matrix
            }


            try {
                if (player.getScheme().getDiceColour(row + 1, column - 1).equals(thecolorofthistile)) {
                    findsamecolordices(row + 1, column - 1, player, list);
                }
            } catch (Exception e) {
                //there is no dice, or you're out of the matrix
                //here you can get a DiceNotExistantException or an OutOfMatrixException
            }
        }catch(Exception e){
            //there is no dice there
            //sorry, you can't get the points
        }

    }




}
