package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.Tile;
import it.polimi.ingsw.model.ToolCard.ToolAction;

import java.rmi.RemoteException;
import java.util.List;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


/**
 * CLI class used to print match elements on screen
 */
public class Printer {
    private static Printer printer;


    /**
     * Private constructor for singleton pattern
     */
    private Printer() {
    }


    /**
     * @return The singleton instance
     */
    public synchronized static Printer Singleton() {
        if (printer == null) {
            printer = new Printer();
        }
        return printer;
    }

    /**
     * This method is used for displaying to the user its scheme card with all the dices put on it
     * @param schemeCard The scheme card object I want to display
     */
    public void printMap( SchemeCard schemeCard)  {
        System.out.println(schemeCard.getMapName());
        System.out.println("Difficoltà della mappa: "+ schemeCard.getDifficulty());
        RowIterator rowIterator =  schemeCard.rowIterator(0);
        while(rowIterator.hasNext()){
            ColumnIterator columnIterator = schemeCard.columnIterator(rowIterator.getCurrentRow());
            while(columnIterator.hasNext()){
                Tile tile = columnIterator.next();
                if (tile.isOccupied()) {
                    try{
                        switch(tile.getDice().getColor()){
                            case YELLOW:
                                System.out.print(ansi().bg(YELLOW).fg(WHITE).a(" " + tile.getDice().getIntensity() + " ").reset());
                                break;
                            case BLUE:
                                System.out.print(ansi().bg(BLUE).fg(WHITE).a(" " + tile.getDice().getIntensity() + " ").reset());
                                break;
                            case RED:
                                System.out.print(ansi().bg(RED).fg(RED).a(" " + tile.getDice().getIntensity() + " ").reset());
                                break;
                            case VIOLET:
                                System.out.print(ansi().bg(MAGENTA).fg(WHITE).a(" " + tile.getDice().getIntensity() + " ").reset());
                                break;
                            case GREEN:
                                System.out.print(ansi().bg(GREEN).fg(WHITE).a(" " + tile.getDice().getIntensity() + " ").reset());
                                break;
                        }
                    }catch (DiceNotExistantException e){
                        //do nothing
                    }
                }
                else if (tile.haveColor_constrain()){
                    switch (tile.getColor_Constrain()){
                        case YELLOW:
                            System.out.print(ansi().bg(YELLOW).fg(WHITE).a("   ").reset());
                            break;
                        case BLUE:
                            System.out.print(ansi().bg(BLUE).fg(WHITE).a("   ").reset());
                            break;
                        case RED:
                            System.out.print(ansi().bg(RED).fg(RED).a("   ").reset());
                            break;
                        case VIOLET:
                            System.out.print(ansi().bg(MAGENTA).fg(WHITE).a("   ").reset());
                            break;
                        case GREEN:
                            System.out.print(ansi().bg(GREEN).fg(WHITE).a("   ").reset());
                            break;
                    }
                }
                else if (tile.haveNumber_constrain()){
                    System.out.print(ansi().bg(WHITE).fg(BLACK).a(" " + tile.getNumber_Constrain() + " ").reset());
                }
                else{
                    System.out.print(ansi().bg(WHITE).fg(BLACK).a("   ").reset());

                }

            }
            System.out.println("");
            rowIterator.next();
        }
        System.out.println();
    }


    /**
     * This method is used during a match to display the private and public goal cards with their names and descriptions
     * taken from the translator
     * @param serverController The controller interface with all the methods exposed by the server
     * @param yourName The username of the account performing the request
     */
    public void printGoalCards(ClientHandlerInterface serverController, String yourName) {
        System.out.println("\n-Ecco gli obiettivi di questa partita-");
        try{
            for(GoalCard goalCard: serverController.getPrivateGoalCard(yourName)){
                System.out.println("\nObiettivo privato:");
                System.out.println(Client.translator.translatePrivateGoalCardName(goalCard.getID()));
                System.out.println(Client.translator.translatePrivateGoalCardDescription(goalCard.getID()));

            }
        }catch(RemoteException e){
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        try{
            for(GoalCard goalCard: serverController.getPublicGoalCards(yourName)){
                System.out.println("\nObiettivo pubblico:");
                System.out.println(Client.translator.translatePublicGoalCardName(goalCard.getID()));
                System.out.println(Client.translator.translatePublicGoalCardDescription(goalCard.getID()));

            }
        }catch(RemoteException e){
            System.out.println(Client.translator.translateException(e.getMessage()));
        }


    }

    /**
     * This method is used during a match to display the situation of the round track
     * @param serverController The controller interface with all the methods exposed by the server
     * @param yourName The username of the account performing the request
     */
    public void printRoundTrack(ClientHandlerInterface serverController, String yourName) {
        System.out.println("Round track:");
        int round = 10;
        int i = 1;
        char[] charDice;

        String[] dices = new String[0];
        try {
            dices = serverController.getRoundTrack(yourName).split("!");
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        for (String diceList : dices) {
            if (i < 10) {
                System.out.print(ansi().bg(WHITE).fg(BLACK).a("  " + i + " ").reset());
            } else System.out.print(ansi().bg(WHITE).fg(BLACK).a(" " + i + " ").reset());
            String[] singleDice = diceList.split("-");
            for (String stringDice : singleDice) {
                charDice = stringDice.toCharArray();
                switch (charDice[1]) {
                    case 'Y':
                        System.out.print(ansi().bg(YELLOW).fg(WHITE).a(" " + charDice[0] + " ").reset());
                        break;
                    case 'B':
                        System.out.print(ansi().bg(BLUE).fg(WHITE).a(" " + charDice[0] + " ").reset());
                        break;
                    case 'R':
                        System.out.print(ansi().bg(RED).fg(WHITE).a(" " + charDice[0] + " ").reset());
                        break;
                    case 'V':
                        System.out.print(ansi().bg(MAGENTA).fg(WHITE).a(" " + charDice[0] + " ").reset());
                        break;
                    case 'G':
                        System.out.print(ansi().bg(GREEN).fg(WHITE).a(" " + charDice[0] + " ").reset());
                        break;
                }
            }
            System.out.println();
            i++;
        }
        System.out.println();
    }


    /**
     * This method is used to display the status of the round dice pool with all the available dices
     * @param serverController The controller interface with all the methods exposed by the server
     * @param yourName The username of the account performing the request
     */
    public void printRoundDicePool(ClientHandlerInterface serverController, String yourName) {
        int index = 0;
        char[] charDice;
        String[] dices = new String[0];

        try {
            dices = serverController.getRoundDicepool(yourName).split("-");
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        System.out.println("Ecco i dadi disponibili in questo round:");
        for (String dice : dices) {
            charDice = dice.toCharArray();
            switch (charDice[1]) {
                case 'Y':
                    System.out.print(ansi().bg(YELLOW).fg(WHITE).a(" " + charDice[0] + " ").reset());
                    break;
                case 'B':
                    System.out.print(ansi().bg(BLUE).fg(WHITE).a(" " + charDice[0] + " ").reset());
                    break;
                case 'R':
                    System.out.print(ansi().bg(RED).fg(WHITE).a(" " + charDice[0] + " ").reset());
                    break;
                case 'V':
                    System.out.print(ansi().bg(MAGENTA).fg(WHITE).a(" " + charDice[0] + " ").reset());
                    break;
                case 'G':
                    System.out.print(ansi().bg(GREEN).fg(WHITE).a(" " + charDice[0] + " ").reset());
                    break;
            }
            System.out.print(" ");
        }
        System.out.println();
        for (String dice : dices) {
            System.out.print(" " + index + "  ");
            index++;
        }
        System.out.println();
    }


    /**
     * This method is used for displaying the name and the desciption of the tool cards available for the current match
     * @param serverController The controller interface with all the methods exposed by the server
     * @param yourName The username of the account performing the request
     */
    public void printToolcard(ClientHandlerInterface serverController, String yourName) {
        int index = 0;
        System.out.println("Queste sono le carte utensile disponibili:");
        try {
            List<ToolAction> list = serverController.getToolCards(yourName);
            for (ToolAction toolAction : list) {
                System.out.println(Client.translator.translateToolCardCardName(toolAction.getID()) + "\nIl costo della carta utensile è: " + toolAction.getCost()
                        + "\nDescrizione:\n" + Client.translator.translateToolCardDescription(toolAction.getID()));
                index++;
            }
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
    }


    /**
     * This method is used to diplay the special dice extracted with the particular pasta salda tool card
     * @param serverController The controller interface with all the methods exposed by the server
     * @param yourName The username of the account performing the request
     */
    public void printExtractedDice(ClientHandlerInterface serverController, String yourName) {
        System.out.println("Questo è il dado estratto:");
        char[] charDice = new char[0];
        try {
            charDice = serverController.getToolCardDice(yourName).toCharArray();
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        switch (charDice[1]) {
            case 'Y':
                System.out.print(ansi().bg(YELLOW).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'B':
                System.out.print(ansi().bg(BLUE).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'R':
                System.out.print(ansi().bg(RED).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'V':
                System.out.print(ansi().bg(MAGENTA).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'G':
                System.out.print(ansi().bg(GREEN).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
        }
        System.out.print(" ");
    }
}
