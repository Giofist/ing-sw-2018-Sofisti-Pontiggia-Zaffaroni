package it.polimi.ingsw.ClientView;

public class StartTurnView implements Runnable {
    @Override
    public void run(){
        try{
           System.out.println("prova");
        }catch (InterruptedException e){
            System.out.println("è finito il tempo per questa operazione");

            return;
        }
    }
}
