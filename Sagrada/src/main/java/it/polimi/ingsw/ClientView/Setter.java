package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
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
        String input;
        boolean success = false;
        boolean correct = false;

        while (!success) {
            System.out.println("Scegli una delle seguenti azioni: ");
            try {
                System.out.println(serverController.getPossibleActions(yourName));
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine().toUpperCase();

            switch (input) {
                case "SETDICE": {
                    placeDice(serverController, yourName);
                    success = true;
                    break;
                }
                case "GETMAPS": {
                    List players = null;
                    List schemecards = null;


                    while (!correct) {
                        try {
                            players = serverController.getPlayersinmymatch(yourName);
                            schemecards = serverController.getSchemeCardsoftheotherPlayers(yourName);
                            correct = true;
                            System.out.println("Ecco le mappe aggiornate degli altri giocatori");
                            int i = 0;
                            while (i < players.size()) {
                                System.out.println(players.get(i).toString());
                                Printer.Singleton().printMap(schemecards.get(i).toString());
                                i++;
                            }
                        } catch (RemoteException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    success = true;
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
                        System.out.println(e.getMessage());
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
                            System.out.println(e.getMessage());
                        }
                    }
                    success = true;
                    break;
                }
                case "SETTOOLCARDDICEINTENSITY": {
                    char[] charDice = new char[0];
                    while(!correct) {
                        System.out.println("Seleziona l'intensità del dado pescao:");
                        try {
                            serverController.setToolCardDiceIntensity(yourName, in.nextInt());
                            correct = true;
                        } catch (RemoteException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    try {
                        charDice = serverController.getToolCardDice(yourName).toCharArray();
                    } catch (RemoteException e) {
                        System.out.println(e.getMessage());
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
        ToolRequestClass data = null;
        String input = "0";
        String[] toolCardID = new String[0];
        int numOfDicesToMove=0;

        try {
            System.out.println("Hai a disposizione "+ serverController.getToken(yourName) + " segnalini favore.");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        Printer.Singleton().printToolcard(serverController, yourName);
        try {
            toolCardID = serverController.getToolCardsIDs(yourName).split("!");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        while (input != toolCardID[0]|| input != toolCardID[1]|| input != toolCardID[2]) {
            System.out.println("Quale carta utensile vuoi usare?");
            input = in.nextLine();
        }
        data.setToolCardID(Integer.parseInt(input));
        switch (input) {
            case "1": { //1. Pinze Sgrossatrice
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado su cui applicare la Pinza Sgrossatrice:");
                data.setSelectedRoundDicepoolDiceIndex(in.nextInt());
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                break;
            }
            case "2": { //2. Pennello per Eglomise
                try {
                    Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Seleziona il dado da spostare. Indica la riga:");
                data.setOldRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setOldColumn1(in.nextInt());
                System.out.println("Seleziona nuova riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Seleziona nuova colonna:");
                data.setNewColumn1(in.nextInt());
                break;
            }
            case "3": { //3. Alesatore per lamina di rame
                try {
                    Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Seleziona il dado da spostare. Indica la riga:");
                data.setOldRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setOldColumn1(in.nextInt());
                System.out.println("Seleziona nuova riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Seleziona nuova colonna:");
                data.setNewColumn1(in.nextInt());
                break;
            }
            case "4": { //4. Lathekin
                try {
                    Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
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
                break;
            }
            case "5": { //5. Taglierina circolare
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado da scambiare con uno del ound Track. Indica l'indice:");
                data.setSelectedDIceIndex(in.nextInt());
                Printer.Singleton().printRoundTrack(serverController, yourName);
                System.out.println("Seleziona il dado da scambiare sulla RoundTrack. Indica il round:");
                data.setRoundWhereThediceis(in.nextInt());
                System.out.println("Indica la posizione: [0/8]");
                data.setSelectedRoundTrackDiceIndex(in.nextInt());
                break;
            }
            case "6": { //6. Pennello per Pasta Salda
                System.out.println("Seleziona il dado da tirare nuovamente. Indica l'indice:");
                data.setSelectedDIceIndex(in.nextInt());
                break;
            }
            case "7": { //7. Martelletto
                break; //nulla da fare ritira tutti i dadi nella riserva
            }
            case "8": { //8. Tenaglia a Rotelle
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado da scambiare con uno del ound Track. Indica l'indice:");
                data.setSelectedDIceIndex(in.nextInt());
                break;
            }
            case "9": { //9. Riga in Sughero
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado da posizionare nella mappa. Indica l'indice:");
                data.setSelectedDIceIndex(in.nextInt());
                try {
                    Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Seleziona la cella isolata in cui piazzarlo. Indica la riga:");
                data.setNewRow1(in.nextInt());
                System.out.println("Indica la colonna:");
                data.setNewColumn1(in.nextInt());
                break;
            }
            case "10": { //10. Tampone Diamantato
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado da girare sulla faccia opposta. Indica l'indice:");
                data.setSelectedDIceIndex(in.nextInt());
                break;
            }
            case "11": { //11. Diluente per Pasta Salda
                Printer.Singleton().printRoundDicePool(serverController, yourName);
                System.out.println("Seleziona il dado rimettere nel sacchetto. Indica l'indice:");
                data.setSelectedDIceIndex(in.nextInt());
                break;
            }
            case "12": { //12. Taglierina Manuale
                try {
                    Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
                while(numOfDicesToMove!=1||numOfDicesToMove!=2) {
                    System.out.println("Seleziona il numero di dadi da spostare: [1/2]");
                    numOfDicesToMove =in.nextInt();
                }
                data.setNumberofDicesyouwanttomove(numOfDicesToMove);
                if (numOfDicesToMove ==1) {
                    System.out.println("Seleziona il primo dado da spostare. Indica la riga:");
                    data.setOldRow1(in.nextInt());
                    System.out.println("Indica la colonna:");
                    data.setOldColumn1(in.nextInt());
                    System.out.println("Seleziona nuova riga:");
                    data.setNewRow1(in.nextInt());
                    System.out.println("Seleziona nuova colonna:");
                    data.setNewColumn1(in.nextInt());
                }
                else {
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
                break;
            }

            default: {
                System.out.println("Hai sbagliato a digitare!");
                break;
            }
        }
        try {
            serverController.useaToolCard(yourName, data);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo che permette di piazzare un dado
    public void placeDice(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        int diceIndex;
        int row = 100;
        int column = 100;
        boolean success = false;
        try {
            Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        while (!success) {
            System.out.println("Seleziona il dado da piazzare usando l'indice numerico sotto riportato:"); //I have to fix with better exception usage
            Printer.Singleton().printRoundDicePool(serverController, yourName);
            diceIndex = in.nextInt();
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/3]");
            row = in.nextInt();
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/4]");
            column = in.nextInt();
            try {
                serverController.setDice(yourName, diceIndex, row, column);
                success = true;
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void placeSingleDice(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        int diceIndex;
        int row = 100;
        int column = 100;
        boolean success = false;
        try {
            Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        while (!success) {
            System.out.println("Piazza il dado appena modificato:");
            Printer.Singleton().printExtractedDice(serverController, yourName);
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/3]");
            row = in.nextInt();
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/4]");
            column = in.nextInt();
            try {
                serverController.setToolCardDice(yourName, row, column);
                success = true;
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void passYourTurn(ClientHandlerInterface serverController, String yourName){
        try {
            serverController.passTurn(yourName);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectSchemeCard(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        String schemeCards = null;
        boolean correct = false;
        int index;
        try {
            for (Object o: serverController.getExtractedSchemeCard(yourName)){
                Printer.Singleton().printMap(o.toString());
            }
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        while(!correct){
            System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo.");
            int selectedCard = in.nextInt();
            try {
                serverController.setSchemeCard(yourName, selectedCard);
                correct = true;
            } catch (RemoteException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void selectExtractedDiceIntensity(ClientHandlerInterface serverController, String yourName){
        Scanner in = new Scanner(System.in);
        int intensity;
        Printer.Singleton().printExtractedDice(serverController, yourName);
        System.out.println("Seleziona l'intensità del dado estratto: [1/6]");
        intensity = in.nextInt();
        try {
            serverController.setToolCardDiceIntensity(yourName, intensity);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

}
