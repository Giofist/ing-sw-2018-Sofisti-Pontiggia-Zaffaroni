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
    public String translateException(String exceptioncode){
        switch(exceptioncode){
            case "1": return "Non puoi mettere il dado qui perchè ce n'è uno dello stesso colore vicino\n"; break;
            case "2": return "Non puoi mettere il dado qui perchè ce n'è uno della stessa intensità vicino\n"; break;
            case "3": return "Non puoi mettere un dado qui, perchè il primo dado deve stare sui bordi\n"; break;
            case "4": return "Non puoi mettere un dado qui, perchè non ce n'è nessuno vicino\n"; break;
            case "5": return "Non stai rispettando le restrizioni di numero\n"; break;
            case "6": return "Non stai rispettando le restrizioni di colore\n"; break;
            case "7": return "Errore in diluente per pasta salda\n"; break;
            case "7.1": return "Errore in diluente per Pasta Salda: non puoi piazzare due dadi nello stesso turno\n"; break;;

            case "9": return "Non stai rispettando qualche restrizione sulla carta schema\n"; break;
            case "10": return "Non puoi mettere un dado qui, perchè c'è già un dado su questa casella\n"; break;
            case "11": return "Errore in alesatore per lamina di rame\n"; break;
            case "12": return "Errore in diluente per Pasta salda\n"; break;
            case "13": return "Errore in Lathekin\n"; break;
            case "14": return "Errore in martelletto\n"; break;
            case "14.1": return "Non puoi giocare Martelletto nel primo turno\n"; break;
            case "14.2": return "Puoi usare Martelletto solo prima di scegliere il secondo dado\n"; break;
            case "15": return "Errore in Pennello per Eglomise\n"; break;
            case "16": return "Errore in pennello per pasta salda\n"; break;
            case "16.1": return "Errore in pennello per pasta salda: non puoi piazzare due dadi nello stesso turno\n"; break;
            case "17": return "Errore in Pinza Sgrossatrice\n"; break;
            case "17.24": return "Errore in pinza sgrossatrice: non puoi diminuire il valore di questo dado\n"; break;
            case "17.29": return "Errore in pinza sgrossatrice: non puoi aumentare il valore di questo dado\n"; break;
            case "18": return "Errore in Riga in Sughero\n"; break;
            case "18.1": return "Errore in riga in suhero: non puoi mettere un dado se ce n'è uno vicino!\n"; break;
            case "18.2": return "Errore in riga in sughero: non puoi piazzare due dadi nello stesso turno\n"; break;
            case "19": return "Errore in Pinza Sgrossatrice\n"; break;
            case "20": return "Errore in Taglierina Manuale\n"; break;
            case "20.1": return "Errore in Taglierina Manuale: Il colore non corrisponde a quello del primo dado\n"; break;
            case "20.2": return "Errore in Taglierina Manuale: Non c'è nessun dado con lo stesso colore nel Tracciato Round\n"; break;
            case "21": return "Errore in tenaglia a rotelle\n"; break;
            case "21.1": return "Errore in tenaglia a rotelle: sei al secondo turno: non puoi usare questa carta al secondo turno!\n"; break;
            case "21.2": return "Errore in tenaglia a rotelle: puoi usare questa carta solo alla fine del turno\n"; break;
            case "22": return "Non puoi farlo!\n"; break;
            case "23": return "Non puoi selezionare una carta che non hai estratto, mi spiace\n"; break;
            case "24": return "Non puoi diminuire il valore di questo dado\n"; break;
            case "25": return "Questa cella non ha un dado che la occupa\n"; break;
            case "26": return "Il sacchetto dei dadi è vuoto o l'indice indicato è sbagliato\n"; break;
            case "27": return "Non esiste nessuna partita con questo nome!\n"; break;
            case "28": return "Questo nome è già utilizzzato da un altro utente\n"; break;
            case "29": return "Non puoi aumentare il valore di questo dado\n"; break;
            case "30": return "Sei già attivo su un altro dispositivo!\n"; break;
            case "31": return "Login errato!\n"; break;
            case "32": return "NOn sono riuscito a leggere il file delle mappe\n"; break;
            case "33": return "Non puoi eseguire nessuna azione, mi spiace\n"; break;
            case "34": return "Non puoi eseguire questa azione, mi spiace\n"; break;
            case "35": return "Non hai abbastanza segnalini per questa toolcard\n"; break;
            case "36": return "Number of players not allowed\n"; break;
            case "37": return "Sei fuori dalla vetrata!\n"; break;
            case "38": return "Questo giocatore non è nella tua partita\n"; break;
            case "39": return "Errore nel caloclo del punteggio dell'obiettivo privato\n"; break;
            case "40": return "Qualche problema sul Tracciato Round\n"; break;
            case "41": return "Non esiste una finestra associata a questo giocatore\n"; break;
            case "42": return "Ci sono due dadi con lo stesso colore\n"; break;
            case "43": return "Ci sono due dadi con la stessa intensità!\n"; break;
            case "44": return "Nessun utente registrato con questo nome: il software lato client ha salvato male il clientname o questo sul server è stato rimosso, " +
                    "la chiamata remota comunque non ha avuto effetto perchè non è stato trovato il player associato al client che ha eseeguito la chiamata stessa\n"; break;
            case "45": return  "Non c'è nessuna toolcard con questo ID nel deck di questa partita, mi spiace\n"; break;





        }
    }

}
