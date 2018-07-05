# ing-sw-2018-Sofisti-Pontiggia-Zaffaroni

## Membri del gruppo


Cognome | Nome | Matricola | Codice persona
------------ | ------------- | ------------- | -------------
Pontiggia | Francesco | 843986 | 10524642
Sofisti | Giorgio | 844664 | 10500171
Zaffaroni | Diego | 844284 | 10499629


## Testing
> inserire

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
>Le principali limitazioni sono collegate al fatto che la CLI non offra la duttilità sufficiente per poter sviluppare un intero videogioco. L'utente che si trova ad usare il Client in modalità CLI infatti deve compiere molte più azioni rispetto alla controparte GUI per compiere le stesse mosse, un esempio è il fatto che ad ogni giocata l'utente deve confermare di passare il turno anche se effettivamente non tocchi a lui (questa scelta è stata obbligata dalla volontà che l'utente potesse richiedere di visionare le Scheme Card degli avversari anche non durante il suo turno ma durante le giocate degli avversari).  
>Per quanto riguarda la GUI invece abbiamo riscontrato problemi nella creazione di un'interfaccia dinamica che si adattasse e scalasse con tutti i formati e risoluzioni degli schermi dunque in alcuni casi con risoluzioni basse o schermi di dimensioni limitate parti dell'interfaccia di gioco potrebbero risultare parzialmente tagliate o non visibili.  
>Infine nostro gruppo ha riposto molta attenzione alla strutturazione del codice secondo i pattern e secondo le “Good practice” di Java parzialmente ignorando l'aspetto di sicurezza soprattutto per quanto riguarda lo scambio di messaggi Client-Server che vengono ora passati in chiaro sul canale.


## Link JAR
> inserire
