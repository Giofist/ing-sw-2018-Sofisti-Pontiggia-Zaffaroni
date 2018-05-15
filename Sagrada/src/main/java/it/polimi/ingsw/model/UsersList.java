package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.UserNotExistantException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.*;


//(pon) ho revisionato questa classe in modo da renderla thread safe in tutti i suoi metodi
public class UsersList {
    private static UsersList instance = null;
    private LinkedList<User> users;

    //in questa classe abbiamo applicato un pattern singleton:
    //la classe deve avere un'unica istanza perchè la lista deve essere unica
    //per il tutto il gioco, altrimenti si rischia di perdere consistenza


    //costruttore privato
    private UsersList(){
        this.users = new LinkedList<User>();
    }


    //metodo che crea/dà accesso se già creata all'unica istanza
    public static  UsersList Singleton(){
        if (instance == null) {
            instance = new UsersList();
            instance.loadUsersList();
        }
        return instance;
    }


    // Questo metodo è privato perchè viene invocato dal costruttore e si occupa di caricare la lista dei giocatori
    // registrati da un file. Se tale file non dovesse esistere la lista users viene inizializzata come vuota.
    // Il formato .csv del file è una serie di lineeeì come segue:
    // username, password\n           (notare la virgola che separa user e pass)
    synchronized private void loadUsersList() {
        File file = new File("./UsersList.csv");
        Scanner fileScanner = null;
        try {
            users = new LinkedList<User>();
            fileScanner = new Scanner(file);
            String[] splitedUsernameAndPass = new String[2];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                splitedUsernameAndPass = line.split(",");
                users.add(new User(splitedUsernameAndPass[0], splitedUsernameAndPass[1]));
            }

        } catch (FileNotFoundException e) {
            users = new LinkedList<User>();
        } catch (NullPointerException e) {
            users = new LinkedList<User>();
        } finally {

        }
    }


    //metodo che controlla il login corretto
    synchronized public boolean check( String name, String password){
        for (User user : this.users){
            if (user.getName() == name && user.getPassword() == password)
                return true;
        }
        return false;
    }


    //classe che permette di registrarsi
    synchronized public User register (String name,String password) {
        User user = new User(name, password);
        this.users.add(user);
        return user;
    }
    // to check Homonymy
    synchronized public void checkHomonymy(String name) throws HomonymyException{
        for (User user:this.users) {
            if(user.getName().equals(name)){
                throw new HomonymyException();
            }
        }
    }

    //rimuovere un'utente che vuole cancellarsi ( opzionale)
    synchronized public void remove (User user){
        this.users.remove(user);
    }

    //metodo getter della lista
    synchronized public LinkedList<User> getUsers() {
        return users;
    }

    synchronized public User getUser(String name) throws UserNotExistantException {
        for (User user: this.users) {
            if (user.getName() == name)
                return user;
        }
        throw new UserNotExistantException();
    }

}
