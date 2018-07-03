package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColumnIterator<T> implements Iterator<Tile> {
    private int  currentColumn=0;
    private int row;
    private boolean deadEnd = false;
    private SchemeCard schemeCard;

    /**
     * This constructor creates an iterator over a Row
     * @param schemeCard The scheme card I want to iterate over
     * @param row The row I want to iterate over
     */
    public ColumnIterator(SchemeCard schemeCard, int row){
        this.schemeCard=schemeCard;
        this.row=row;
    }


    /**
     * This method returns true if in the scheme card there is another tile in the same row of the current position
     * of the iterator and in the following column
     * @return Boolean value whether or not there is another Tile
     */
    @Override
    public boolean hasNext() {
        // Situation in case we have an invalid Column Iterator initialization
        if (this.row < 0) {
            throw new NoSuchElementException("Problem in constructing ColumnIterator.");
        }
        return !deadEnd;
    }


    /**
     * This method doesn't do anything
     */
    @Override
    public void remove() {
        // To be implemented
    }


    /**
     * @return Returns the next tile, with same row and following column, in the scheme card
     */
    @Override
    public Tile next() {
        Tile nextElement;
        try {
            nextElement = schemeCard.getTile(row, currentColumn);
        } catch (OutOfMatrixException e) {
            throw new NoSuchElementException("Matrix dead end reached.");
        }
        currentColumn++;
        if (currentColumn == schemeCard.getMaxColumn()) {
            deadEnd = true;
        }
        return nextElement;
    }


    /**
     * @return The column at which the iterator is currently at
     */
    public int getCurrentColumn(){
        return currentColumn;
    }
}
