package it.polimi.ingsw.model;
import java.net.Socket;
import java.util.*;

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
    static  UsersList Singleton(){
        if (instance == NULL)
            instance = new UsersList();
        return instance;
    }


    public boolean check( String name, String password){
        for (User user : this.users){
            if (user.getName() == name && user.getPassword == password)
                return true;
        }
        return false;
    }
    public User register (String name,String password, Socket socket ) throws Homonymy_Exception{
        this.control_homonymy(name);
        User user = new User(name, password, socket);
        this.users.add(user);
        return user;
    }
    public boolean remove (User user){

    }
    public control_homonymy(String name){
        for
    }


}
