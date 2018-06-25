package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.TurnActions;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class Setter {
    private static Setter setter;
    //costruttore privato
    private Setter(){ }

    //metodo che crea/dà accesso se già creata all'unica istanza
    public static Setter Singleton(){
        if (setter == null) {
            setter = new Setter();
        }
        return setter;
    }

    //metodo che serve per selozioanre possibili azioni
    public void selectAction(ClientHandlerInterface serverController, String yourName) {
        Scanner in = new Scanner(System.in);

        boolean success = false;
        boolean correct = false;

        while (!success) {
            System.out.println("Scegli una delle seguenti azioni: ");
            try {
                for(TurnActions turnActions: serverController.getPossibleActions(yourName)){
                    System.out.println(Client.translator.translateTurnAction(turnActions));
                };
            } catch (RemoteException e) {
                System.out.println(Client.translator.translateException(e.getMessage()));
            }
            String input1 = in.nextLine();
            String input = Client.translator.detranslateTurnAction(input1);
            switch (input) {
                case "SETDICE": {
                    placeDice(serverController, yourName);
                    success = true;
                    break;
                }
                case "GETMAPS": {
                    List<Player> players;
                    List<SchemeCard> schemecards;
                    System.out.println("Adesso ti mostreremo le mappe degli altri");

                    while (!correct) {
                        try {
                            players = serverController.getPlayersinmymatch(yourName);
                            schemecards = serverController.getSchemeCardsoftheotherPlayers(yourName);
                            correct = true;
                            System.out.println("Ecco le mappe aggiornate degli altri giocatori");
                            int i = 0;
                            for (Player player: players) {
                                System.out.println(Client.translator.translatePlayer(player));
                                Printer.Singleton().printMap(Client.translator.translateSchemeCard(schemecards.get(i)));
                                i++;
                            }
                        } catch (RemoteException e) {
                            System.out.println(Client.translator.translateException(e.getMessage()));
                        }
                    }
                    correct = false;
                    break;
                }
                case "USEALLTOOLCARD": {
                    useToolcard(serverController, yourName);
                    success = true;
                    break;
                }
                case "LEAVEMATCHATTHEEND": {
                    try {
                        serverController.leavethematchatthend(yourName);
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                    success = true;
                    break;
                }
                case "SETTOOLCARDDICE": {
                    while (!correct) {
                        System.out.println("Seleziona la riga in cui posizionare il dado: [0/3]");
                        int row = in.nextInt();
                        System.out.println("Seleziona la riga in cui posizionare il dado: [0/4]");
                        int column = in.nextInt();
                        try {
                            serverController.setToolCardDice(yourName, row, column);
                            correct = true;
                        } catch (RemoteException e) {
                            System.out.println(Client.translator.translateException(e.getMessage()));
                        }
                    }
                    success = true;
                    break;
                }
                case "SETTOOLCARDDICEINTENSITY": {
                    char[] charDice = new char[0];
                    while(!correct) {
                        System.out.println("Seleziona l'intensità del dado pescato:");
                        try {
                            serverController.setToolCardDiceIntensity(yourName, in.nextInt());
                            correct = true;
                        } catch (RemoteException e) {
                            System.out.println(Client.translator.translateException(e.getMessage()));
                        }
                    }
                    try {
                        charDice = serverController.getToolCardDice(yourName).toCharArray();
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                    switch(charDice[1]){
                        case 'Y':
                            System.out.print( ansi().eraseScreen().bg(YELLOW).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'B':
                            System.out.print( ansi().eraseScreen().bg(BLUE).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'R':
                            System.out.print( ansi().eraseScreen().bg(RED).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'V':
                            System.out.print( ansi().eraseScreen().bg(MAGENTA).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'G':
                            System.out.print( ansi().eraseScreen().bg(GREEN).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                    }
                    System.out.println();
                    success = true;
                    break;
                }
                case "SETSCHEMECARD": { //metodo da non usare eventualmente valuterò
                    System.out.println("Comando non usabile!");
                    break;
                }
                case "PASSTURN": {
                    passYourTurn(serverController, yourName);
                    success = true;
                    break;
                }
                default:{
                    System.out.println("Hai sbagliato a digitare!");
                    break;
                }
            }
        }
    }

    public void useToolcard(ClientHandlerInterface serverController, String yourName) {
        Scanner in = new Scanner(System.in);
        ToolRequestClass data = new ToolRequestClass();
        String input = "0";
        String[] toolCardID = new String[0];
        Boolean correct = false;
        Boolean success = false;
        Boolean condition = false;
        int numOfDicesToMove=0;

        try {
            System.out.println("Hai a disposizione "+ serverController.getToken(yourName) + " segnalini favore.");
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        Printer.Singleton().printToolcard(serverController, yourName);
        System.out.println("Quale carta utensile vuoi usare?");
        input = in.nextLine();
        data.setToolCardID(Integer.parseInt(input));
        switch (input) {
            case "1": { //1. Pinze Sgrossatrice
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                while(!condition){
                System.out.println("Seleziona il dado su cui applicare la Pinza Sgrossatrice:");
                data.setSelectedDiceIndex(in.nextInt());
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "2": { //2. Pennello per Eglomise
                try {
                    Printer.Singleton().printMap(Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
                while(!condition){
                System.out.println("Seleziona il dado da spostare. Indica la riga:");
                data.setOldRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setOldColumn1(in.nextInt());
                System.out.println("Seleziona nuova riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Seleziona nuova colonna:");
                data.setNewColumn1(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "3": { //3. Alesatore per lamina di rame
                try {
                    Printer.Singleton().printMap(Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
                while(!condition){
                System.out.println("Seleziona il dado da spostare. Indica la riga:");
                data.setOldRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setOldColumn1(in.nextInt());
                System.out.println("Seleziona nuova riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Seleziona nuova colonna:");
                data.setNewColumn1(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "4": { //4. Lathekin
                try {
                    Printer.Singleton().printMap(Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
                while(!condition){
                System.out.println("Seleziona il primo dado da spostare. Indica la riga:");
                data.setOldRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setOldColumn1(in.nextInt());
                System.out.println("Seleziona nuova riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Seleziona nuova colonna:");
                data.setNewColumn1(in.nextInt());
                System.out.println("Seleziona il secondo dado da spostare. Indica la riga:");
                data.setOldRow2(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setOldColumn2(in.nextInt());
                System.out.println("Seleziona nuova riga:");
                data.setNewRow2(in.nextInt());
                System.out.println("Seleziona nuova colonna:");
                data.setNewColumn2(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "5": { //5. Taglierina circolare
                while(!condition){
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado da scambiare con uno del ound Track. Indica l'indice:");
                data.setSelectedDiceIndex(in.nextInt());
                Printer.Singleton().printRoundTrack(serverController, yourName);
                System.out.println("Seleziona il dado da scambiare sulla RoundTrack. Indica il round:");
                data.setRoundWhereThediceis(in.nextInt());
                System.out.println("Indica la posizione: [0/8]");
                data.setSelectedRoundTrackDiceIndex(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "6": { //6. Pennello per Pasta Salda
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                while(!condition){
                System.out.println("Seleziona il dado da tirare nuovamente. Indica l'indice:");
                data.setSelectedDiceIndex(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "7": { //7. Martelletto
                break; //nulla da fare ritira tutti i dadi nella riserva
            }
            case "8": { //8. Tenaglia a Rotelle
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                while(!condition){
                System.out.println("Seleziona il dado da scambiare con uno del Round Track. Indica l'indice:");
                data.setSelectedDiceIndex(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "9": { //9. Riga in Sughero
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                while(!condition){
                System.out.println("Seleziona il dado da posizionare nella mappa. Indica l'indice:");
                data.setSelectedDiceIndex(in.nextInt());
                try {
                    Printer.Singleton().printMap(Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
                System.out.println("Seleziona la cella isolata in cui piazzarlo. Indica la riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setNewColumn1(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "10": { //10. Tampone Diamantato
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                while(!condition){
                System.out.println("Seleziona il dado da girare sulla faccia opposta. Indica l'indice:");
                data.setSelectedDiceIndex(in.nextInt());
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }
            case "11": { //11. Diluente per Pasta Salda
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                while(!condition){
                System.out.println("Seleziona il dado rimettere nel sacchetto. Indica l'indice:");
                data.setSelectedDiceIndex(in.nextInt());
                try {
                    serverController.useaToolCard(yourName, data);
                    condition = true;
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
            }
                break;
            }
            case "12": { //12. Taglierina Manuale
                try {
                    Printer.Singleton().printMap(Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
                while(!success) {
                    System.out.println("Seleziona il numero di dadi da spostare: [1/2]");
                    numOfDicesToMove = in.nextInt();
                    if(numOfDicesToMove>0 || numOfDicesToMove<3){
                        success = true;
                    }
                }
                data.setNumberofDicesyouwanttomove(numOfDicesToMove);
                while(!condition) {
                    if (numOfDicesToMove == 1) {
                        System.out.println("Seleziona il primo dado da spostare. Indica la riga:");
                        data.setOldRow1(in.nextInt());
                        System.out.println("Indica la colonna:");
                        data.setOldColumn1(in.nextInt());
                        System.out.println("Seleziona nuova riga:");
                        data.setNewRow1(in.nextInt());
                        System.out.println("Seleziona nuova colonna:");
                        data.setNewColumn1(in.nextInt());
                    } else {
                        System.out.println("Seleziona il primo dado da spostare. Indica la riga:");
                        data.setOldRow1(in.nextInt());
                        System.out.println("Indica la colonna:");
                        data.setOldColumn1(in.nextInt());
                        System.out.println("Seleziona nuova riga:");
                        data.setNewRow1(in.nextInt());
                        System.out.println("Seleziona nuova colonna:");
                        data.setNewColumn1(in.nextInt());
                        System.out.println("Seleziona il primo dado da spostare. Indica la riga:");
                        data.setOldRow1(in.nextInt());
                        System.out.println("Indica la colonna:");
                        data.setOldColumn1(in.nextInt());
                        System.out.println("Seleziona nuova riga:");
                        data.setNewRow1(in.nextInt());
                        System.out.println("Seleziona nuova colonna:");
                        data.setNewColumn1(in.nextInt());
                    }
                    try {
                        serverController.useaToolCard(yourName, data);
                        condition = true;
                    } catch (RemoteException e) {
                        System.out.println(Client.translator.translateException(e.getMessage()));
                    }
                }
                break;
            }

            default: {
                System.out.println("Hai sbagliato a digitare!");
                break;
            }
        }

    }

    //metodo che permette di piazzare un dado
    public void placeDice(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        int diceIndex;
        int row = 100;
        int column = 100;
        boolean success = false;
        boolean correct = false;
        try {
            Printer.Singleton().printMap( Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        while (!success) {
            correct = false;
            System.out.println("Seleziona il dado da piazzare usando l'indice numerico sotto riportato:"); //I have to fix with better exception usage
            Printer.Singleton().printRoundDicePool(serverController, yourName);
            diceIndex = in.nextInt();
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/3]");
            while(!correct) {
                try {
                    row = Integer.parseInt(in.nextLine());
                    correct = true;
                } catch (Exception e) {
                    System.out.println("Hai sbagliato a digitare!");
                }
            }
            correct = false;
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/4]");
            while(!correct) {
                try {
                    column = Integer.parseInt(in.nextLine());
                    correct = true;
                } catch (Exception e) {
                    System.out.println("Hai sbagliato a digitare!");
                }
            }
            try {
                serverController.setDice(yourName, diceIndex, row, column);
                success = true;
            } catch (RemoteException e) {
                System.out.println(Client.translator.translateException(e.getMessage()));
            }
        }
    }

    public void placeSingleDice(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        int diceIndex;
        int row = 100;
        int column = 100;
        boolean success = false;
        boolean correct = false;
        try {
            Printer.Singleton().printMap(Client.translator.translateSchemeCard(serverController.getSchemeCard(yourName).get(0)));
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        while (!success) {
            correct = false;
            System.out.println("Piazza il dado appena modificato:");
            Printer.Singleton().printExtractedDice(serverController, yourName);
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/3]");
            while(!correct) {
                try {
                    row = Integer.parseInt(in.nextLine());
                    correct = true;
                } catch (Exception e) {
                    System.out.println("Hai sbagliato a digitare!");
                }
            }
            correct = false;
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/4]");
            while(!correct) {
                try {
                    column = Integer.parseInt(in.nextLine());
                    correct = true;
                } catch (Exception e) {
                    System.out.println("Hai sbagliato a digitare!");
                }
            }
            try {
                serverController.setToolCardDice(yourName, row, column);
                success = true;
            } catch (RemoteException e) {
                System.out.println(Client.translator.translateException(e.getMessage()));
            }
        }
    }

    public void passYourTurn(ClientHandlerInterface serverController, String yourName){
        try {
            serverController.passTurn(yourName);
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
    }

    public void selectSchemeCard(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        String schemeCards = null;
        int selectedCard = 50;
        boolean correct = false;
        boolean good = false;
        int index;
        try {
            for (SchemeCard schemeCard: serverController.getExtractedSchemeCard(yourName)){
                Printer.Singleton().printMap(Client.translator.translateSchemeCard(schemeCard));
            }
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        while(!correct){
            good = false;
            System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo.");
            while(!good) {
                try {
                    selectedCard = Integer.parseInt(in.nextLine());
                    good = true;
                } catch (Exception e) {
                    System.out.println("Hai sbagliato a digitare!");
                }
            }
            try {
                serverController.setSchemeCard(yourName, selectedCard);
                correct = true;
            } catch (RemoteException e){
                System.out.println(Client.translator.translateException(e.getMessage()));
            }
        }
    }

    public void selectExtractedDiceIntensity(ClientHandlerInterface serverController, String yourName) {
        Scanner in = new Scanner(System.in);
        int intensity = 0;
        boolean good = false;
        boolean correct = false;

        while (!correct) {
            good = false;
            Printer.Singleton().printExtractedDice(serverController, yourName);
            System.out.println("Seleziona l'intensità del dado estratto: [1/6]");
            while (!good) {
                try {
                    intensity = Integer.parseInt(in.nextLine());
                    good = true;
                } catch (Exception e) {
                    System.out.println("Hai sbagliato a digitare!");
                }
            }
            try {
                serverController.setToolCardDiceIntensity(yourName, intensity);
                correct = true;
            } catch (RemoteException e) {
                System.out.println(Client.translator.translateException(e.getMessage()));
            }
        }
    }

}
