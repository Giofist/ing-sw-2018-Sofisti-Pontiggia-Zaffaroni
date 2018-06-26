package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.IsAlreadyActiveException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import it.polimi.ingsw.model.PlayerPackage.Player;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;


public class UsersList {
    private static UsersList instance = null;
    private LinkedList<User> users;



    //costruttore privato
    private UsersList(){
        this.users = new LinkedList<User>();
    }


    //metodo che crea/dà accesso se già creata all'unica istanza
    public static  UsersList Singleton() {
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
        FileReader fr = null;
        Scanner fileScanner = null;
        try {
            fr = new FileReader("src/main/resources/UsersList.txt");
            fileScanner = new Scanner(fr);
            users = new LinkedList<User>();
            String[] splittedUsernameAndPass;

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                splittedUsernameAndPass = line.split(",");
                users.add(new User(splittedUsernameAndPass[0], splittedUsernameAndPass[1]));
            }
        } catch (IOException e){
            users = new LinkedList<User>();
        } finally {
            try { fr.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            try {
                fileScanner.close();
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }


    // ho creato LoginException, ma sicome esiste già una classe loginExcpetion in una libreria standard di java, allora devo scrivere tutto il package
    synchronized public void check( String name, String password, Observer observer)throws it.polimi.ingsw.model.Exceptions.LoginException, IsAlreadyActiveException {
        String hexHash = produceSHA256(password);
        for (User user : this.users){
            if (user.getName().equals(name) && user.getPassword().equals(hexHash)){
                if (user.isActive()){
                    throw new IsAlreadyActiveException();
                }else {
                    user.setActive(true);
                    if (user.getPlayer() != null) {
                        user.getPlayer().getPlayerState().addObserver(observer);
                        user.getPlayer().setPlayerState(user.getPlayer().getPlayerState().getState());
                    }
                }
                return;
            }
        }
        throw new it.polimi.ingsw.model.Exceptions.LoginException();
    }

    synchronized public void logOut( String name){
        for (User user : this.users){
            if (user.getName().equals(name))
            user.setActive(false);
        }
    }

    //classe che permette di registrarsi
    synchronized public void register (String name,String password) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String hexHash = produceSHA256(password);
            // Write the new User to the file
            fw = new FileWriter("src/main/resources/UsersList.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(name + "," + hexHash);
            bw.newLine();
            bw.flush();

            System.out.println(name+","+hexHash);

            // Add the new user to the list of registered users
            User user = new User(name, hexHash);
            this.users.add(user);

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try { fw.close(); } catch (Exception e) { e.printStackTrace(); }
            try { bw.close(); } catch (Exception e) { }
        }
        return;
    }

    private String produceSHA256(String password) {
        MessageDigest digest;
        byte[] hash;
        StringBuffer hexHash = new StringBuffer();
        try {
            // Create the SHA-256 of the password
            digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Convert hash bytes into StringBuffer
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexHash.append('0');
                hexHash.append(hex);
            }
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hexHash.toString();
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
    synchronized public User getUser(String name) throws UserNotExistentException {
        for (User user: this.users) {
            if (user.getName().equals(name))
                return user;
        }
        throw new UserNotExistentException();
    }


    // Useful for testing
    synchronized protected int getUsersListSize() { return this.users.size(); }
}
