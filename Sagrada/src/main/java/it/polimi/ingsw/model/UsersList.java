package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Exceptions.HomonymyException;

import java.net.Socket;
import java.util.*;


//(pon) ho revisionato questa classe in modo da renderla thread safe in tutti i suoi metodi
public class UsersList {
    private static UsersList instance;
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
        if (instance == null)
            instance = new UsersList();
        return instance;
    }

    //metodo che controlla il login corretto
    public boolean check( String name, String password){
        for (User user : this.users){
            if (user.getName() == name && user.getPassword() == password)
                return true;
        }
        return false;
    }

    //classe che permette di registrarsi
    public User register (String name,String password, Socket socket ) throws HomonymyException {
        User user = new User(name, password, socket);
        this.users.add(user);
        return user;
    }
    // to check Homonymy
    public void checkHomonymy(String name) throws HomonymyException{
        for (User user:this.users) {
            if(user.getName().equals(name)){
                throw new HomonymyException();
            }
        }
    }

    //rimuovere un'utente che vuole cancellarsi ( opzionale)
    public void remove (User user){
        this.users.remove(user);
    }

    //metodo getter della lista
    public LinkedList<User> getUsers() {
        return users;
    }

    public User getUser(String name, String password) throws Exception{
        for (User user: this.users) {
            if (user.getName() == name && user.getPassword() == password)
                return user;
        }
        throw new Exception();
    }


}
