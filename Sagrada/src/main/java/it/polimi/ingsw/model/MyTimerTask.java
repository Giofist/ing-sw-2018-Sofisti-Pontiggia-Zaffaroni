package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Turn.Turn;

import java.util.Date;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    Turn turn;
    public MyTimerTask(Turn turn){
        this.turn = turn;
    }
    @Override
    public void run() {
        System.out.println("Turn started at:"+new Date());
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Turn ended at:"+new Date());
        Thread.currentThread().interrupt();



    }
}
