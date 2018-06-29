package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;

import java.util.LinkedList;
import java.util.List;

//sarebbe il piazzato round
public class RoundTrack {
    private DicePool round1Dices;
    private DicePool round2Dices;
    private DicePool round3Dices;
    private DicePool round4Dices;
    private DicePool round5Dices;
    private DicePool round6Dices;
    private DicePool round7Dices;
    private DicePool round8Dices;
    private DicePool round9Dices;
    private DicePool round10Dices;

    /**
     * Creates an empty round track
     */
    public RoundTrack(){
        round1Dices = new DicePool();
        round2Dices = new DicePool();
        round3Dices = new DicePool();
        round4Dices = new DicePool();
        round5Dices = new DicePool();
        round6Dices = new DicePool();
        round7Dices = new DicePool();
        round8Dices = new DicePool();
        round9Dices = new DicePool();
        round10Dices = new DicePool();
    }


    /**
     * @param round The round number from where I want to get the dices
     * @return A DicePool containing all the dices of the round track from the specified tile
     * @throws RoundTrackException Exception thrown if there is an error in accessing the tiles on the round track
     */
    public DicePool getroundTrackDices (int round) throws RoundTrackException {
        switch (round) {
            case 1: return  this.round1Dices;
            case 2: return  this.round2Dices;
            case 3: return  this.round3Dices;
            case 4: return  this.round4Dices;
            case 5: return  this.round5Dices;
            case 6: return  this.round6Dices;
            case 7: return  this.round7Dices;
            case 8: return  this.round8Dices;
            case 9: return  this.round9Dices;
            case 10: return this.round10Dices;
            default: throw new RoundTrackException();
        }
    }


    /**
     * This method places a list of dices on a specific tile of the round track
     * @param round The round where I want to place my dices
     * @param dices A list containing all the dices I want to place
     * @throws RoundTrackException Exception thrown if there is an error in accessing the tiles on the round track
     */
    public void setRoundTrackDices(int round, List dices) throws RoundTrackException{
        switch (round){
            case 1: this.round1Dices.addallDices(dices); break;
            case 2: this.round2Dices.addallDices(dices); break;
            case 3: this.round3Dices.addallDices(dices); break;
            case 4: this.round4Dices.addallDices(dices);break;
            case 5: this.round5Dices.addallDices(dices);break;
            case 6: this.round6Dices.addallDices(dices);break;
            case 7: this.round7Dices.addallDices(dices);break;
            case 8: this.round8Dices.addallDices(dices);break;
            case 9: this.round9Dices.addallDices(dices);break;
            case 10: this.round10Dices.addallDices(dices);break;
            default: throw new RoundTrackException();
        }
    }

    /**
     * This method returns a list with all the colors of the dices placed of the round track
     * @return List with all the colors
     * @throws RoundTrackException Exception thrown if there is an error in accessing the tiles on the round track
     */
    public List<DiceColor> allColors() throws RoundTrackException {
        List list = new LinkedList<DiceColor>();
        for (int i=1; i<11; i++){
            for(int j=0; j<getroundTrackDices(i).getDicePoolSize();j++){
                try{
                    list.add(getroundTrackDices(i).getDice(j).getColor());
                }catch(DicepoolIndexException e){
                }
            }
        }
        return list;
    }


    @Override
    public String toString() {
        String roundTrackString = "";
        roundTrackString += round1Dices.toString();
        roundTrackString += "!";
        roundTrackString += round2Dices.toString();
        roundTrackString += "!";
        roundTrackString += round3Dices.toString();
        roundTrackString += "!";
        roundTrackString += round4Dices.toString();
        roundTrackString += "!";
        roundTrackString += round5Dices.toString();
        roundTrackString += "!";
        roundTrackString += round6Dices.toString();
        roundTrackString += "!";
        roundTrackString += round7Dices.toString();
        roundTrackString += "!";
        roundTrackString += round8Dices.toString();
        roundTrackString += "!";
        roundTrackString += round9Dices.toString();
        roundTrackString += "!";
        roundTrackString += round10Dices.toString();
        roundTrackString += "!";
        return roundTrackString;
    }
}
