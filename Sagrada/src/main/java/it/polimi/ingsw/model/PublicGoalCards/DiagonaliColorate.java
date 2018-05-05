package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;


//revisionata by pon
//obiettivo pubblico
public class DiagonaliColorate implements GoalCard {
    static int ID = 9;
    static String name = "Diagonali Colorate";
    static String description = "Numero di dadi dello stesso colore diagonalmente adiacenti.";
    @Override
    public void calculatepoint(Player player) {
        for(int row=0; row <4; row++){
            for(int column =0; column <5; column++){
                LinkedList<Dice> list = new LinkedList<Dice>();
                findsamecolordices(row,column,player,list);
                if(list.size() >1){
                    player.addPoints(list.size());
                }
            }
        }




    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public String getDescription() {
        return description;
    }
    //It could be kind to rewrite this expression in functional Java, coudn't it?
    //But at the time, if it work, it's the top!
    //my only doubt is about swallow or deep copy of the list: if it'not swallow, it does not work
    //so please if you've some news, inform me
    private void findsamecolordices(int row, int column,Player player,LinkedList<Dice> list){
        try {


            DiceColor thecolorofthistile = player.getScheme().getDiceColour(row, column);
            list.add(player.getScheme().removeDice(row, column));



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
            //zorry, you can't get the points
        }

    }




}
