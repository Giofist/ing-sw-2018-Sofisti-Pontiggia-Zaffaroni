package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.Player;

public class ItalianTranslator implements Translator {

    @Override
    public String translateMatch(Match match) {
        String string = "";
        string += match.getName() + " " + "Giocatori in questa partita:";
        for (Player player: match.getallPlayers()){
            string += this.translatePlayer(player);
        }
        return string;
    }


    @Override
    public String translatePlayer(Player player){
        return "Nome del giocatore " + player.getName();
    };

    @Override
    public String getPublicGoalCardDescription(int cardID){
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

    @Override
    public String getPublicGoalCardName(int cardID){
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

    @Override
    public String getPrivateGoalCardDescription(int cardID){
        switch (cardID) {
            case 1: return "Somma dei valori su tutti i dadi ROSSI.";
            case 2: return "Somma dei valori su tutti i dadi GIALLI.";
            case 3: return "Somma dei valori su tutti i dadi VERDI.";
            case 4: return "Somma dei valori su tutti i dadi BLU.";
            case 5: return "Somma dei valori su tutti i dadi VIOLA.";
            default: return null;
        }
    }

    @Override
    public String getPrivateGoalCardName(int cardID){
        switch (cardID) {
            case 1: return "Sfumature ROSSE.";
            case 2: return "Sfumature GIALLE.";
            case 3: return "Sfumature VERDI.";
            case 4: return "Sfumature BLU.";
            case 5: return "Sfumature VIOLA.";
            default: return null;
        }
    }

    @Override
    public String translateException(String exceptioncode){
        switch(exceptioncode){
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
            case "19": return "Errore in Pinza Sgrossatrice\n";
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
            case "36": return "Number of players not allowed\n";
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
