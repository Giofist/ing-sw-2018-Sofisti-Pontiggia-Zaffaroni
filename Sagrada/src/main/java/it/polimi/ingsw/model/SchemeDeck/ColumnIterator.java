package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColumnIterator<T> implements Iterator<Tile> {
    private int  currentColumn=0;
    private int row;
    private boolean deadEnd = false;
    private SchemeCard schemeCard;

    public ColumnIterator(SchemeCard schemeCard, int row){
        this.schemeCard=schemeCard;
        this.row=row;
    }

    @Override
    public boolean hasNext() {
        // Situation in case we have an invalid Column Iterator initialization
        if (this.row < 0) {
            throw new NoSuchElementException("Problem in constructing ColumnIterator.");
        }
        return !deadEnd;
    }

    @Override
    public void remove() {
        // To be implemented
    }



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
    public int getCurrentColumn(){
        return currentColumn;
    }
}
