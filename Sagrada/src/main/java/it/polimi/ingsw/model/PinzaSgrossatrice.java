package it.polimi.ingsw.model;

public class PinzaSgrossatrice implements ToolAction {
    final static int ID = 1;
    final static String description = "";
    Player player;

    public PinzaSgrossatrice(Player player){
        this.player = player;
    }
    @Override
    public void execute(){
        //in questa classe chiama player.gametable.get dice e poi comunica al client chiedendo
        //che cosa voglia fare, settadno poi eventuali cambiamenti tramite thi.player.setdice. eccc

    }

}
