package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TurnActions;

/**
 * Class containing all the italian names and descriptions of the cards in the game and other methods for getting information
 * about players and matches
 */
public class ItalianTranslator implements Translator {

    /**
     * @param match The match from where to get information
     * @return The match name and the players currently in the match's waiting room
     */
    @Override
    public String translateMatch(Match match) {
        String string = "";
        string += match.getName() + " " + "Giocatori in questa partita:" + "\n";
        for (Player player: match.getallPlayers()){
            string += this.translatePlayer(player);
        }
        return string;
    }


    /**
     * @param player A player in the match
     * @return A string containing "Nome del giocatore " + its name
     */
    @Override
    public String translatePlayer(Player player){
        return "Nome del giocatore " + player.getName();
    };


    /**
     * @param cardID The public goal card's id of which we want to retrieve information
     * @return The description of the specified public goal card
     */
    @Override
    public String translatePublicGoalCardDescription(int cardID){
        switch (cardID) {
            case 1: return "Righe senza colori ripetuti.";
            case 2: return "Colonne senza colori ripetuti.";
            case 3: return "Righe senza sfumature ripetute.";
            case 4: return "Colonne senza sfumature ripetute.";
            case 5: return "Set di 1 & 2 ovunque.";
            case 6: return "Set di 3 & 4 ovunque.";
            case 7: return "Set di 5 & 6 ovunque.";
            case 8: return "Set di dadi di ogni valore ovunque.";
            case 9: return "Numero di dadi dello stesso colore diagonalmente adiacenti.";
            case 10: return "Set di dadi di ogni colore ovunque.";
            default: return null;
        }
    }

    /**
     * @param cardID The public goal card's id of which we want to retrieve information
     * @return The name of the specified public goal card
     */
    @Override
    public String translatePublicGoalCardName(int cardID){
        switch (cardID) {
            case 1: return "Colori Diversi - Riga";
            case 2: return "Colori Diversi - Colonna";
            case 3: return "Sfumature diverse - Riga";
            case 4: return "Sfumature Diverse - Colonna";
            case 5: return "Sfumature Chiare";
            case 6: return "Sfumature Medie";
            case 7: return "Sfumature Scure";
            case 8: return "Sfumature Diverse";
            case 9: return "Diagonali Colorate";
            case 10: return "Varietà di Colore";
            default: return null;
        }
    }

    /**
     * @param cardID The tool card's id of which we want to retrieve information
     * @return The description of the specified tool card
     */
    @Override
    public String translateToolCardDescription(int cardID){
        switch (cardID) {
            case 1: return "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1.\nNon puoi cambiare un 6 in 1 o un 1 in 6.";
            case 2: return "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\nDevi rispettare tutte le altre restrizioni di piazzamento.";
            case 3: return "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\nDevi rispettare tutte le altre restrizioni di piazzamento.";
            case 4: return "Muovi esattamente due dadi.\nRispetta tutte le restrizioni di piazzamento.";
            case 5: return "Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato Round.\n";
            case 6: return "Dopo aver scelto un dado tira nuovamente quel dado.\nSe non puoi piazzarlo, riponilo nella riserva.";
            case 7: return "Tira nuovamente tutti i dadi della riserva.\nQuesta carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.";
            case 8: return "Dopo il tuo primo turno scegli immediatamente un altro dado.\nSalta il secondo turno di questo round.";
            case 9: return "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado.\nDevi rispettare tutte le restrizioni di piazzamento.";
            case 10: return "Dopo aver scelto un dado, giralo sulla faccia opposta.\n6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.";
            case 11: return "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal sacchetto.\nScegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";
            case 12: return "Muovi fino a due dadi dello stesso colore  di un solo dado sul Tracciato dei Round.\nDevi rispettare tutte le restrizioni di piazzamento.";
            default: return null;
        }
    }

    /**
     * @param cardID The tool card's id of which we want to retrieve information
     * @return The name of the specified tool card
     */
    @Override
    public String translateToolCardCardName(int cardID){
        switch (cardID) {
            case 1: return "Pinza Sgrossatrice";
            case 2: return "Pennello per Eglomise";
            case 3: return "Alesatore per lamina di rame";
            case 4: return "Lathekin";
            case 5: return "Taglierina circolare";
            case 6: return "Pennello per Pasta Salda";
            case 7: return "Martelletto";
            case 8: return "Tenaglia a Rotelle";
            case 9: return "Riga in Sughero";
            case 10: return "Tampone Diamantato";
            case 11: return "Diluente per Pasta Salda";
            case 12: return "Taglierina Manuale";
            default: return null;
        }
    }


    /**
     * @param cardID The private goal card's id of which we want to retrieve information
     * @return The description of the specified private goal card
     */
    @Override
    public String translatePrivateGoalCardDescription(int cardID){
        switch (cardID) {
            case 1: return "Somma dei valori su tutti i dadi ROSSI.";
            case 2: return "Somma dei valori su tutti i dadi GIALLI.";
            case 3: return "Somma dei valori su tutti i dadi VERDI.";
            case 4: return "Somma dei valori su tutti i dadi BLU.";
            case 5: return "Somma dei valori su tutti i dadi VIOLA.";
            default: return "Non sono stato in grado di leggere l'obiettivo privato";
        }
    }

    /**
     * @param turnActions Action available depending on the state
     * @return A string describing the action in more detail
     */
    public String translateTurnAction(TurnActions turnActions){
        switch (turnActions){
            case SETDICE: return "Piazzare un dado";
            case GETMAPS: return "Visualizzare le mappe aggiornate degli altri giocatori";
            case PASSTURN: return "Passare il tuo turno";
            case LEAVEMATCH: return "Lasciare la partita corrente";
            case USEALLTOOLCARD: return "Usare una carta utensile";
            case SETTOOLCARDDICE: return "Posizionare il dado estratto nell'utilizzo della carta utensile";
            case SETTOOLCARDDICEINTENSITY: return "Scegliere il valore del dado estratto nell'utilizzo della carta utensile";
            case SETSCHEMECARD: return "Scegliere quale carta schema usare nella partita corrente";
            default: return "Error";
        }
    }

    /**
     * @param turnaction A string describing the action we want to perform
     * @return The equivalent encoded action
     */
    @Override
    public String detranslateTurnAction(String turnaction){
        switch(turnaction){
            case "Piazzare un dado": return "SETDICE";
            case "Visualizzare le mappe aggiornate degli altri giocatori": return "GETMAPS";
            case "Passare il tuo turno": return "PASSTURN";
            case "Lasciare la partita corrente, che è terminata": return "LEAVEMATCH";
            case "Usare una carta utensile": return "USEALLTOOLCARD";
            case "Posizionare il dado estratto nell'utilizzo della carta utensile": return "SETTOOLCARDDICE";
            case "Scegliere il valore del dado estratto nell'utilizzo della carta utensile": return "SETTOOLCARDDICEINTENSITY" ;
            case "Scegliere quale carta schema usare nella partita corrente": return "SETSCHEMECARD";
            case "Lasciare la partita corrente prima che inizi": return "LEAVEMATCH";
            default: return "Error";
        }
    };


    /**
     * @param cardID The private goal card's id of which we want to retrieve information
     * @return The name of the specified private goal card
     */
    @Override
    public String translatePrivateGoalCardName(int cardID){
        switch (cardID) {
            case 1: return "Sfumature ROSSE.";
            case 2: return "Sfumature GIALLE.";
            case 3: return "Sfumature VERDI.";
            case 4: return "Sfumature BLU.";
            case 5: return "Sfumature VIOLA.";
            default: return null;
        }
    }

    /**
     * @param exceptioncode Code corresponding to the exception message we want to throw
     * @return The message we want to bind to the exception we are about to throw
     */
    @Override
    public String translateException(String exceptioncode){
        String[] strings =exceptioncode.split(" ");
        switch(strings[strings.length-1]){
            case "1": return "Non puoi mettere il dado qui perchè ce n'è uno dello stesso colore vicino\n";
            case "2": return "Non puoi mettere il dado qui perchè ce n'è uno della stessa intensità vicino\n";
            case "3": return "Non puoi mettere un dado qui, perchè il primo dado deve stare sui bordi\n";
            case "4": return "Non puoi mettere un dado qui, perchè non ce n'è nessuno vicino\n";
            case "5": return "Non stai rispettando le restrizioni di numero\n";
            case "6": return "Non stai rispettando le restrizioni di colore\n";
            case "7": return "Errore in diluente per pasta salda\n";
            case "7.1": return "Errore in diluente per Pasta Salda: non puoi piazzare due dadi nello stesso turno\n";


            case "9": return "Non stai rispettando qualche restrizione sulla carta schema\n";
            case "10": return "Non puoi mettere un dado qui, perchè c'è già un dado su questa casella\n";
            case "11": return "Errore in alesatore per lamina di rame\n";
            case "12": return "Errore in diluente per Pasta salda\n";
            case "13": return "Errore in Lathekin\n";
            case "14": return "Errore in martelletto\n";
            case "14.1": return "Non puoi giocare Martelletto nel primo turno\n";
            case "14.2": return "Puoi usare Martelletto solo prima di scegliere il secondo dado\n";
            case "15": return "Errore in Pennello per Eglomise\n";
            case "16": return "Errore in pennello per pasta salda\n";
            case "16.1": return "Errore in pennello per pasta salda: non puoi piazzare due dadi nello stesso turno\n";
            case "17": return "Errore in Pinza Sgrossatrice\n";
            case "17.24": return "Errore in pinza sgrossatrice: non puoi diminuire il valore di questo dado\n";
            case "17.29": return "Errore in pinza sgrossatrice: non puoi aumentare il valore di questo dado\n";
            case "18": return "Errore in Riga in Sughero\n";
            case "18.1": return "Errore in riga in suhero: non puoi mettere un dado se ce n'è uno vicino!\n";
            case "18.2": return "Errore in riga in sughero: non puoi piazzare due dadi nello stesso turno\n";
            case "19": return "Errore in Taglierina circolare\n";
            case "20": return "Errore in Taglierina Manuale\n";
            case "20.1": return "Errore in Taglierina Manuale: Il colore non corrisponde a quello del primo dado\n";
            case "20.2": return "Errore in Taglierina Manuale: Non c'è nessun dado con lo stesso colore nel Tracciato Round\n";
            case "21": return "Errore in tenaglia a rotelle\n";
            case "21.1": return "Errore in tenaglia a rotelle: sei al secondo turno: non puoi usare questa carta al secondo turno!\n";
            case "21.2": return "Errore in tenaglia a rotelle: puoi usare questa carta solo alla fine del turno\n";
            case "22": return "Non puoi farlo!\n";
            case "23": return "Non puoi selezionare una carta che non hai estratto, mi spiace\n";
            case "24": return "Non puoi diminuire il valore di questo dado\n";
            case "25": return "Questa cella non ha un dado che la occupa\n";
            case "26": return "Il sacchetto dei dadi è vuoto o l'indice indicato è sbagliato\n";
            case "27": return "Non esiste nessuna partita con questo nome!\n";
            case "28": return "Questo nome è già utilizzzato da un altro utente\n";
            case "29": return "Non puoi aumentare il valore di questo dado\n";
            case "30": return "Sei già attivo su un altro dispositivo!\n";
            case "31": return "Login errato!\n";
            case "32": return "NOn sono riuscito a leggere il file delle mappe\n";
            case "33": return "Non puoi eseguire nessuna azione, mi spiace\n";
            case "34": return "Non puoi eseguire questa azione, mi spiace\n";
            case "35": return "Non hai abbastanza segnalini per questa toolcard\n";
            case "36": return "La partita è già iniziata, mi spiace\n";

            case "37": return "Sei fuori dalla vetrata!\n";
            case "38": return "Questo giocatore non è nella tua partita\n";
            case "39": return "Errore nel caloclo del punteggio dell'obiettivo privato\n";
            case "40": return "Qualche problema sul Tracciato Round\n";
            case "41": return "Non esiste una finestra associata a questo giocatore\n";
            case "42": return "Ci sono due dadi con lo stesso colore\n";
            case "43": return "Ci sono due dadi con la stessa intensità!\n";
            case "44": return "Nessun utente registrato con questo nome: il software lato client ha salvato male il clientname o questo sul server è stato rimosso, " +
                    "la chiamata remota comunque non ha avuto effetto perchè non è stato trovato il player associato al client che ha eseeguito la chiamata stessa\n";
            case "45": return  "Non c'è nessuna toolcard con questo ID nel deck di questa partita, mi spiace\n";
            default: return exceptioncode;




        }
    }

}
