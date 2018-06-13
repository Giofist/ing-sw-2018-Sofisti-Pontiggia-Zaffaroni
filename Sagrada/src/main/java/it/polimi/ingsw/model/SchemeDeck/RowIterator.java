package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowIterator<T> implements Iterator<Tile> {
    private int currentRow=0;
    private int column;
    private SchemeCard schemeCard;
    private boolean deadEnd = false;

    public RowIterator(SchemeCard schemeCard,int column){
        this.schemeCard = schemeCard;
        this.column= column;
    }
    @Override
    public boolean hasNext() {
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
            nextElement = schemeCard.getTile(currentRow, column);
        } catch (OutOfMatrixException e) {
            throw new NoSuchElementException("Matrix dead end reached.");
        }

        System.out.println("ROW: " + currentRow + " COL: " + column);
        currentRow++;
        System.out.println("ROW: " + currentRow + " COL: " + column);
        if (currentRow == schemeCard.getMaxRow()) {
            deadEnd = true;
        }
        return nextElement;
    }
    public int getCurrentRow(){
        return currentRow;
    }

}
