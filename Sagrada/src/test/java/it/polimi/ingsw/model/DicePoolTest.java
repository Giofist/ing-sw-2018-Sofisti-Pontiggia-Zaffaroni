package it.polimi.ingsw.model;

public class DicePoolTest {
    public static void main(String[] args){
        DicePool dicePool = new DicePool();
        System.out.println("BEFORE SCRAMBLING");
        System.out.println(dicePool.toString());
        dicePool.scrambleDicePool();
        System.out.println("AFTER SCRAMBLING");
        System.out.println(dicePool.toString());
    }

}
