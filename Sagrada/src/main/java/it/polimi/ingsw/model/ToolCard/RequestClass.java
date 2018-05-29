package it.polimi.ingsw.model.ToolCard;

import java.io.Serializable;

public class RequestClass implements Serializable {
    private int ToolCardID;
    private int oldRow1;
    private int oldColumn1;
    private int newRow1;
    private int newColumn1;
    private int oldRow2;
    private int oldColumn2;
    private int newRow2;
    private int newColumn2;
    private int selectedDIceIndex;
    private int operationforPinzaSgrossatrice;
    private int selectedRoundDicepoolDiceIndex;
    private int roundWhereThediceis;
    private int selectedRoundTrackDiceIndex;
    private int diceIntesityToset;


    public int getSelectedRoundTrackDiceIndex() {
        return selectedRoundTrackDiceIndex;
    }

    public void setSelectedRoundTrackDiceIndex(int selectedRoundTrackDiceIndex) {
        this.selectedRoundTrackDiceIndex = selectedRoundTrackDiceIndex;
    }

    public int getRoundWhereThediceis() {
        return roundWhereThediceis;
    }

    public void setRoundWhereThediceis(int roundWhereThediceis) {
        this.roundWhereThediceis = roundWhereThediceis;
    }

    public int getSelectedRoundDicepoolDiceIndex() {
        return selectedRoundDicepoolDiceIndex;
    }

    public void setSelectedRoundDicepoolDiceIndex(int selectedRoundDicepoolDiceIndex) {
        this.selectedRoundDicepoolDiceIndex = selectedRoundDicepoolDiceIndex;
    }

    public int getOperationforPinzaSgrossatrice() {
        return operationforPinzaSgrossatrice;
    }

    public void setOperationforPinzaSgrossatrice(int operationforPinzaSgrossatrice) {
        this.operationforPinzaSgrossatrice = operationforPinzaSgrossatrice;
    }

    public int getSelectedDIceIndex() {
        return selectedDIceIndex;
    }

    public void setSelectedDIceIndex(int selectedDIceIndex) {
        this.selectedDIceIndex = selectedDIceIndex;
    }

    public int getNewColumn2() {
        return newColumn2;
    }

    public void setNewColumn2(int newColumn2) {
        this.newColumn2 = newColumn2;
    }

    public int getNewRow2() {
        return newRow2;
    }

    public void setNewRow2(int newRow2) {
        this.newRow2 = newRow2;
    }

    public int getOldColumn2() {
        return oldColumn2;
    }

    public void setOldColumn2(int oldColumn2) {
        this.oldColumn2 = oldColumn2;
    }

    public int getOldRow2() {
        return oldRow2;
    }

    public void setOldRow2(int oldRow2) {
        this.oldRow2 = oldRow2;
    }

    public int getNewColumn1() {
        return newColumn1;
    }

    public void setNewColumn1(int newColumn1) {
        this.newColumn1 = newColumn1;
    }

    public int getNewRow1() {
        return newRow1;
    }

    public void setNewRow1(int newRow1) {
        this.newRow1 = newRow1;
    }

    public int getOldColumn1() {
        return oldColumn1;
    }

    public void setOldColumn1(int oldColumn1) {
        this.oldColumn1 = oldColumn1;
    }

    public int getOldRow1() {
        return oldRow1;
    }

    public void setOldRow1(int oldRow1) {
        this.oldRow1 = oldRow1;
    }

    public int getDiceIntesityToset() {
        return diceIntesityToset;
    }

    public void setDiceIntesityToset(int diceIntesityToset) {
        this.diceIntesityToset = diceIntesityToset;
    }

    public int getToolCardID() {
        return ToolCardID;
    }

    public void setToolCardID(int toolCardID) {
        ToolCardID = toolCardID;
    }
}
