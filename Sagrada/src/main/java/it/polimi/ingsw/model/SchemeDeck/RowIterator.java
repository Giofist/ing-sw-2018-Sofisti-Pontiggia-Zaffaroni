package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowIterator<T> implements Iterator<Tile> {
    private int currentRow=0;
    private int column;
    private SchemeCard schemeCard;
    private boolean deadEnd = false;

    /**
     * This constructor creates an iterator over a Column
     * @param schemeCard The scheme card I want to iterate over
     * @param column The column I want to iterate over
     */
    public RowIterator(SchemeCard schemeCard,int column){
        this.schemeCard = schemeCard;
        this.column= column;
    }


    /**
     * This method returns true if in the scheme card there is another tile in the same column of the current position
     * of the iterator and in the following row
     * @return Boolean value whether or not there is another Tile
     */
    @Override
    public boolean hasNext() {
        // Situation in case we have an invalid Column Iterator initialization
        if (this.column < 0) {
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
     * @return Returns the next tile, with same column and following row, in the scheme card
     */
    @Override
    public Tile next() {
        Tile nextElement;
        try {
            nextElement = schemeCard.getTile(currentRow, column);
        } catch (OutOfMatrixException e) {
            throw new NoSuchElementException("Matrix dead end reached.");
        }
        currentRow++;
        if (currentRow == schemeCard.getMaxRow()) {
            deadEnd = true;
        }
        return nextElement;
    }


    /**
     * @return The column at which the iterator is currently at
     */
    public int getCurrentRow(){
        return currentRow;
    }

}
