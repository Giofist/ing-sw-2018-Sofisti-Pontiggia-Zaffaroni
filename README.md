# ing-sw-2018-Sofisti-Pontiggia-Zaffaroni

## Membri del gruppo


Cognome | Nome | Matricola | Codice persona
------------ | ------------- | ------------- | -------------
Pontiggia | Francesco | 843986 | 10524642
Sofisti | Giorgio | 844664 | 10500171
Zaffaroni | Diego | 844284 | 10499629


## Testing
Per il testing abbiamo riscontrato alcuni problemi con le metriche di sonar, pertanto al seguente link è possibile visualizzare un report generato con le metriche di intellij. E' sufficiente aprire index.html per poter navigare nei risultati.  
Le exception sono state testate nei relativi metodi dove vengono lanciate.
> https://github.com/Giofist/ing-sw-2018-Sofisti-Pontiggia-Zaffaroni/tree/master/report%20testing

## Diagramma UML
> UML Server + Controller + Model + Cli https://github.com/Giofist/ing-sw-2018-Sofisti-Pontiggia-Zaffaroni/blob/master/FINL.jpg  
> UML GUI : https://github.com/Giofist/ing-sw-2018-Sofisti-Pontiggia-Zaffaroni/blob/master/umlGUI.jpg

## Funzionalità implementate
1. Regole complete del gioco
2. Connettività sia Socket sia RMI
3. Client sia GUI sia CLI
4. Possibilità di caricare carte schema personalizzate da file
5. Gestione di partite multiple simultanee da parte del server
6. Possibilità di registrare un proprio account persistente sul server

## Limitazioni riscontrate

>Su Cli: per visualizzare correttamente i dadi e la carta Schema, è necessario installare https://github.com/adoxa/ansicon/releases/tag/v1.84 su windows(mentre non è neccesario fare nulla su Linux) perchè l'encoding ansi altrimenti non è supportato; inoltre, sempre su CLI, ogni giocatore deve "passare il turno" anche se non è il suo turno, altrimenti bisogna aspettare lo scadere del timer. Il server aspetta che tutti i quattro giocatori "passino il turno", per cui se uno di loro si disconnette durante il turno, bisogna aspettare il timer. In Gui il "passa Turno " è chiamato in automatico dal codice client per i giocatori che non sono nel loro turno, per cui si deve aspettare il timer solo se il giocatore che è nel suo turno si disconnette. Non siamo riusciti a implementare un meccanismo di esclusione dalla partita di chi si disconnette o non fa le sue mosse prima della scadenza del timer, per cui ad ogni turno il server cerca comunque di contattare tutti i giocatori segnalando l'inizio di un nuovo turno, e, se uno dei giocatori risulta disconnesso, aspetta un "passaTurno" in meno ( Implementato con un CountDownSignal).
Abbiamo implementato un meccanismo di controllo degli accessi, per cui un giocatore può accedere al proprio account soltanto su un dispositivo alla volta. Questo ci evita parecchi problemi di concorrenza (il giocatore non può giocare alla stessa partita su due dispositivi diversi facendo in contemporanea mosse in conflitto tra loro). Dei ping controllano periodicamente che i giocatori connessi non si siano disconnessi nel frattempo, ma non sono in grado di segnalare questo al turno corrente. Proprio per la presenza di questi ping, se un giocatore si disconnette o chiude forzatamente la connessione, deve aspettare al massimo un minuto (il periodo di ping), durante il quale se tenta di connettersi riceverà l'eccezione "sei già attivo su di altro dispositivo".
La limitazione principale della connessione socket è che sia server che client eseguono codice ricevuto dalla rete, perchè quando ricevono un messaggio chiamano message.performAction(), senza curarsi che questo messaggio non contenga codice malevolo (tra l'altro passando come parametro il proprio controller/view e il listener) . 
Della wait() non contenute in cicli while in SocketController, SocketClient e Round sono esposte a eventuali spurious wake up.
La lista dei giocatori e delle partite è implementato con il singleton Pattern(); l'uso delle carte utensili e il calcolo dei punti a fine partita tramite command Pattern; l'aggiornamento della view  di cambio di stato è effettuato tramite l'observer Pattern. Gli stati sono implementato tramite enum, ad ogni stato sono associate le possibili azioni.
Dall'UML si vede chiaramente che la classe player è soggetto a più "riferimenti reciproci" (con Turn, Match): la classe ha anche molti attributi interni, e in generale è troppo interdipendente rispetto alle altre classi del model, creando difficoltà di manutenzione e upgrade. Tuttavia è la classe attraverso il quale il controller accede al model relativo al giocatore che ha chiamato un metodo del controller stesso, quindi si può considerare una sorta di "punto d'accesso" per il controller al model. 
Un limite del controller è invece che il controller, per quanto usi sempre il player come punto di accesso, ha visione completa del model: sarebbe preferibile che chiamasse i metodi di una sola classe e avesse visibilità solo di quella. Abbiamo evitato questa scelta perchè avrebbe appesantito ulteriormente la classe player.
Sulla GUI abbiamo un bottone "Single Player": non abbiamo scelto di implementare questa funzionalità (il bottone non conduce a niente).
I tempi dei timer sono i seguenti:
-quando viene creata una partita, prima che inizi: 5 minuti (se il numero di giocatori scende sotto i 2, il timer viene resettato)
-per scegliere una carta Schema: 1 minuto (dopo il quale chi ha scelto la carta schema si trova assegnata di default la prima tra le possibili)
-per un turno: 3 minuti (dopo il quale il turno termina forzatamente).
Il timer del turno non è in grado, come già detto, di escludere il giocatore corrente, nè di notificare agli altri giocatori che un particolare giocatore si è disconnesso (e questa è la principale difformità rispetto alle specifiche), ma il codice di Turn è in grado di rilevare se è rimasto un solo giocatore attivo e tutti gli altri si sono disconnessi: in tal caso, vince a tavolino.
Sulla Gui abbiamo avuto dei problemi relativi al caso in cui un giocatore preme esc e poi riapre l'interfaccia di gioco: nonostante siano stati quasi completamente risolti, la grafica potrebbe ancora dare problemi di giocabilità su dispositivi con particolari dimensioni e risoluzioni ( per esempio non si vedono.
All'avvio del server, viene caricata la lista dei giocatori registrati da un file txt: il  System.out stampa all'amministratore di sistema nome e password (criptata) ogni volta che un nuovo utente si registra. 
Quando un giocatore esegue il login, se una partita in cui giocava prima di disconnettersi è ancora attiva, il giocatore rientra subito in partita, ricevendo un'update con lo stato in cui si trova: questo potrebbe essere poco user-friendly (soprattutto sulla Cli).
Su sistemi operativi Linux si riscontrano problemi con connessione RMI: si consiglia socket.

Per quanto riguarda il testing, abbiamo avuto dei problemi con Sonar a generare la coverage: riportiamo la coverage generata tramite IntelliJ




## Link JAR
I jar sono disponibili al seguente link della repository. Prima di avviare il server è necessario creare nella stessa cartella un file chiamato "UsersList.txt"
> https://github.com/Giofist/ing-sw-2018-Sofisti-Pontiggia-Zaffaroni/tree/master/jars
