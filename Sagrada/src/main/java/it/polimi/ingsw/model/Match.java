package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CountDownLatch;

//manca solo il timer
public class Match implements Runnable,Serializable{
    private String game_name;
    private LinkedList<Player> players;
    private transient Gametable gametable;
    private transient boolean started;
    private transient CountDownLatch doneSignal;
    private transient boolean isreadyTostart;
    private transient Timer timer;
    private transient Round round;


    /**
     * Create a new match and adds the creator to the list of players. After the creation the match is not yet ready to start with only one player
     * @param player The player creator of the game
     * @param game_name The name which will be displayed in the match list
     */
    public Match(Player player, String game_name)  {
        this.game_name = game_name;
        this.players = new LinkedList<>();
        this.players.addFirst(player);
        player.setMatch(this);
        this.isreadyTostart = false;
        this.timer = new Timer();
        this.round = null;
    }


    /**
     * Main method which controls the whole game, from the choice of the SchemeCards to the calculation of the final score
     * by setting up each round and spawning a new round thread
     */
    @Override
    public void run(){
        synchronized (this){
            while (!isreadyTostart){
                try {
                    this.players.getLast().setPlayerState(State.MATCHNOTSTARTEDYETSTATE);
                    wait();
                }catch(InterruptedException e) {
                    return;
                }
            }
        }

        this.setStarted(true);
        try {
            gametable = new Gametable(this.players.size());
            for (Player player : this.players) {
                boolean success = false;
                while (!success) {
                    try {
                        player.setPrivateGoalCard(getGametable().getPrivateGoalCard());
                        player.addExtractedSchemeCard(getGametable().getSchemeCard());
                        player.addExtractedSchemeCard(getGametable().getSchemeCard());
                        player.setPlayerState(State.MUSTSETSCHEMECARD);
                        success = true;
                    } catch (MapConstrainReadingException e) {
                        System.out.println(e.getMessage());
                    }catch(PrivateGoalCardException e){
                        // do nothing
                    }
                }

            }
        }catch(IOException e){
            //do something?
        }

        doneSignal = new CountDownLatch(this.getNumberOfPlayers());
        Timer schemeCardTimer = new Timer(false);
        schemeCardTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(90000);
                    if(doneSignal.getCount()==0){
                        return;
                    }
                    else for (Player player: players){
                        try{
                            player.getScheme();
                        }catch (SchemeCardNotExistantException e){
                            try{
                                player.setScheme(player.getExtractedSchemeCards().get(0).getID());
                            }catch(CardIdNotAllowedException er){
                                //do nothing, impossibile to get here
                            }
                        }
                    }
                    while(doneSignal.getCount() !=0){
                        doneSignal.countDown();
                    }
                    return;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        },0);
        try {
            doneSignal.await();
        }catch(InterruptedException e) {
            //do nothing
        }
        // Now the game can start for N rounds
        for (int i = 1; i<10; i++){
            // This method prepares the round by extracting the new dices in the round dicepool
            this.getGametable().setupRound();
            this.round = new Round(this.players);
            this.round.run();
            // At each round I have to change the order of the players
            this.players.addLast(this.players.removeFirst());
            // The round can terminate, all the dices from the RoundDicePool will be moved on the RoundTrack
            try{
                this.getGametable().endRound(i);
            }catch (RoundTrackException e){
                // Situation in which we try to edit a round which is not on the RoundTrack
            }
        }


        // This method prepares the round by extracting the new dices in the round dicepool
        this.getGametable().setupRound();
        this.round = new Round(this.players);
        this.round.run();
        try{
            this.getGametable().endRound(10);
        }catch (RoundTrackException e){
            // Situation in which we try to edit a round which is not on the RoundTrack
        }


        //to calculate points for the public goals
        this.getGametable().calculatePointsforAllPlayers(this.players);
        // to calculate points for the private goals
        for (Player player:this.players) {
            player.getPrivateGoalCard().calculatepoint(player);
        }

        for (Player player:this.players) {
            player.setPlayerState(State.ENDMATCHSTATE);
        }
    }





    // Getters and setters methods

    /**
     * @return The round in which we are
     */
    public Round getRound(){
        return this.round;
    }

    /**
     * @return The name of the match
     */
    public String getName() {
        return this.game_name;
    }


    /**
     * @return How many players are in the current match
     */
    public int getNumberOfPlayers() {
        return this.players.size();
    }


    /**
     * @return The game table associated to the match
     */
    public Gametable getGametable() {
        return gametable;
    }


    /**
     * @return True if the match is already started
     */
    public boolean isStarted() {
        return started;
    }


    /**
     * Set the status of the match whether is started or not
     * @param started Booleane value to which the status will be set
     */
    public void setStarted(boolean started){
        this.started= started;
    }


    /**
     * This methods allows a player to join the current match
     * @param player Player who wants to join
     */
    public void join(Player player){
        this.players.addLast(player);
        final Match match = this;
        if(this.players.size()==2) {
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    synchronized (match){
                        if(!isStarted()){
                            if (match.getallPlayers().size() >1){
                                isreadyTostart = true;
                                match.notifyAll();
                            }
                            else{
                                // The only player must leave the match
                                for(Player player: match.getallPlayers()){
                                    player.setPlayerState(State.ERRORSTATE);
                                }
                                MatchesList.singleton().remove(match);
                                UsersList.Singleton().getUser(player.getName()).removePlayer(player.getPlayerState().getObserver());
                            }
                        }
                    }
                }
            },120000);
        }
        if (this.players.size() == 3){
            isreadyTostart = true;
        }


    }


    /**
     * Method used to know all the opponents of param Player in the match, by alphabetic order
     * @param player Player to be excluded from the returned list
     * @return List of Players
     */
    public List<Player> getallPlayersbutnotme(Player player){
        LinkedList<Player> list = new LinkedList<>();
        list.addAll(this.players);
        list.remove(player);
        Collections.sort(list, (Comparator.comparing(Player::getName)

        ));
        return list;
    }


    /**
     * This method returns a list of all the player in the current match
     * @return List of Players
     */
    public List<Player> getallPlayers(){
        LinkedList<Player> list = new LinkedList<>();
        list.addAll(this.players);
        return list;
    }


    /**
     * This method allows a player to leave the current match
     * @param player The Player which wants to leave
     */
    public void leavethematch(Player player){
        this.players.remove(player);
        if(this.players.size() ==1){
            this.timer.cancel();
        }
        if(getNumberOfPlayers()==0){
            MatchesList.singleton().remove(this);
        }
    }


    /**
     * This method is useful for notifying the game when we chose our tool card and we are ready to start as players
     */
    public void countDown() {
        this.doneSignal.countDown();
    }

    /**
     * This method terminates the match in case the conditions to go on are not met
     */
    public void forceendmatch() {
        for(Player player: this.players){
            if (UsersList.Singleton().getUser(player.getName()).isActive()){
                player.setPlayerState(State.FORCEENDMATCH);
            }
            else {
                player.getMatch().leavethematch(player);
                UsersList.Singleton().getUser(player.getName()).removePlayer(player.getPlayerState().getObserver());
            }
        }
    }


    // For testing
    protected boolean getIsReadyToStart(){ return isreadyTostart;  }
    protected void setIsReadToStart(boolean ready) { isreadyTostart = ready; }
}
