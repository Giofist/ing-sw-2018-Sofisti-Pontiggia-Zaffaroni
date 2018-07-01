package it.polimi.ingsw.model.ToolCard;

import java.io.Serializable;

/**
 *  This class contains all the possible parameters necessary to any tool card to be performed
 */
public class ToolRequestClass implements Serializable {
    private int ToolCardID;
    private int numberofDicesyouwanttomove;
    private int oldRow1;
    private int oldColumn1;
    private int newRow1;
    private int newColumn1;
    private int oldRow2;
    private int oldColumn2;
    private int newRow2;
    private int newColumn2;
    private int selectedDiceIndex;
    private int operationforPinzaSgrossatrice;
    private int roundWhereThediceis;
    private int selectedRoundTrackDiceIndex;


    /**
     * @return The index of the dice we want to select from the round track
     */
    public int getSelectedRoundTrackDiceIndex() {
        return selectedRoundTrackDiceIndex;
    }

    /**
     * @param selectedRoundTrackDiceIndex The index of the dice we want to select from the round track
     */
    public void setSelectedRoundTrackDiceIndex(int selectedRoundTrackDiceIndex) {
        this.selectedRoundTrackDiceIndex = selectedRoundTrackDiceIndex;
    }



    /**
     * @return The number of the round on the round track that we want to access
     */
    public int getRoundWhereThediceis() {
        return roundWhereThediceis;
    }

    /**
     * @param roundWhereThediceis The number of the round on the round track that we want to access
     */
    public void setRoundWhereThediceis(int roundWhereThediceis) {
        this.roundWhereThediceis = roundWhereThediceis;
    }



    /**
     * @return What type of operation we want to perform with pinza sgrossatrice. 0: decrease, any other number: increase
     */
    public int getOperationforPinzaSgrossatrice() {
        return operationforPinzaSgrossatrice;
    }

    /**
     * @param operationforPinzaSgrossatrice What type of operation we want to perform with pinza sgrossatrice. 0: decrease, any other number: increase
     */
    public void setOperationforPinzaSgrossatrice(int operationforPinzaSgrossatrice) {
        this.operationforPinzaSgrossatrice = operationforPinzaSgrossatrice;
    }



    /**
     * @return The index of the dice (in the round dice pool) that we want to access
     */
    public int getSelectedDiceIndex() {
        return selectedDiceIndex;
    }

    /**
     * @param selectedDIceIndex  The index of the dice (in the round dice pool) that we want to access
     */
    public void setSelectedDiceIndex(int selectedDIceIndex) {
        this.selectedDiceIndex = selectedDIceIndex;
    }



    /**
     * @return The column where we want to place the second dice
     */
    public int getNewColumn2() {
        return newColumn2;
    }

    /**
     * @param newColumn2 The column where we want to place the second dice
     */
    public void setNewColumn2(int newColumn2) {
        this.newColumn2 = newColumn2;
    }



    /**
     * @return The row where we want to place the second dice
     */
    public int getNewRow2() { return newRow2; }

    /**
     * @param newRow2 The row where we want to place the second dice
     */
    public void setNewRow2(int newRow2) {
        this.newRow2 = newRow2;
    }



    /**
     * @return The column from where we want to pick the second dice
     */
    public int getOldColumn2() {
        return oldColumn2;
    }

    /**
     * @param oldColumn2 The column from where we want to pick the second dice
     */
    public void setOldColumn2(int oldColumn2) {
        this.oldColumn2 = oldColumn2;
    }



    /**
     * @return The row from where we want to pick the second dice
     */
    public int getOldRow2() {
        return oldRow2;
    }

    /**
     * @param oldRow2 The row from where we want to pick the second dice
     */
    public void setOldRow2(int oldRow2) {
        this.oldRow2 = oldRow2;
    }



    /**
     * @return The column where we want to place the first dice
     */
    public int getNewColumn1() {
        return newColumn1;
    }

    /**
     * @param newColumn1 The column where we want to place the first dice
     */
    public void setNewColumn1(int newColumn1) {
        this.newColumn1 = newColumn1;
    }



    /**
     * @return The row where we want to place the first dice
     */
    public int getNewRow1() {
        return newRow1;
    }

    /**
     * @param newRow1 The row where we want to place the first dice
     */
    public void setNewRow1(int newRow1) {
        this.newRow1 = newRow1;
    }



    /**
     * @return The column from where we want to pick the first dice
     */
    public int getOldColumn1() {
        return oldColumn1;
    }

    /**
     * @param oldColumn1 The column from where we want to pick the first dice
     */
    public void setOldColumn1(int oldColumn1) {
        this.oldColumn1 = oldColumn1;
    }



    /**
     * @return The row from where we want to pick the first dice
     */
    public int getOldRow1() {
        return oldRow1;
    }

    /**
     * @param oldRow1 The row from where we want to pick the first dice
     */
    public void setOldRow1(int oldRow1) {
        this.oldRow1 = oldRow1;
    }



    /**
     * @return The id of the tool card that we want to use
     */
    public int getToolCardID() {
        return ToolCardID;
    }

    /**
     * @param toolCardID The id of the tool card that we want to use
     */
    public void setToolCardID(int toolCardID) {
        ToolCardID = toolCardID;
    }



    /**
     * @return The number of dices we want to move (used for taglierina manuale)
     */
    public int getNumberofDicesyouwanttomove() {
        return numberofDicesyouwanttomove;
    }

    /**
     * @param numberofDicesyouwanttomove The number of dices we want to move (used for taglierina manuale)
     */
    public void setNumberofDicesyouwanttomove(int numberofDicesyouwanttomove) {
        this.numberofDicesyouwanttomove = numberofDicesyouwanttomove;
    }
}
