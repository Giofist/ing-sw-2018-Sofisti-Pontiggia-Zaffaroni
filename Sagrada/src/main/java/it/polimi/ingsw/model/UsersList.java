package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.IsAlreadyActiveException;
import it.polimi.ingsw.model.Exceptions.LoginException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class UsersList {
    private static UsersList instance = null;
    private Hashtable<String, User> users;



    //costruttore privato
    private UsersList(){
        this.users = new Hashtable<>();
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
     private void loadUsersList() {
        FileReader fr = null;
        Scanner fileScanner = null;
        try {
            fr = new FileReader("src/main/resources/UsersList.txt");
            fileScanner = new Scanner(fr);
            users = new Hashtable<>();
            String[] splittedUsernameAndPass;

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                splittedUsernameAndPass = line.split(",");
                users.put(splittedUsernameAndPass[0], new User(splittedUsernameAndPass[0], splittedUsernameAndPass[1]));
            }
        } catch (IOException e){
            users = new Hashtable<>();
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
    public synchronized void check( String name, String password, Observer observer)throws it.polimi.ingsw.model.Exceptions.LoginException, IsAlreadyActiveException {
        String hexHash = produceSHA256(password);
        if(this.users.containsKey(name)){
            User user = this.users.get(name);
            if(user.getPassword().equals(hexHash)){
                if(user.isActive()){
                    throw new IsAlreadyActiveException();
                }
                else{
                    user.setActive(true);
                    user.getUserState().addObserver(observer);
                    Timer timer = new Timer(false);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                while(user.isActive()){
                                    Thread.sleep(60000);
                                    try{
                                        user.getUserState().notifyObservers();
                                        System.out.println("Ho notificato gli observer, e li ho trovati attivi "+ user.getName());
                                    }catch(RemoteException e){
                                        user.setActive(false);
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                return;
                            }
                            return;
                        }
                    },0);
                }
                if(user.getPlayer() != null){
                    user.getPlayer().setPlayerState(user.getPlayer().getPlayerState().getState());
                }
                return;
            }
        }
        throw new LoginException();

    }

    public void logOut( String name, Observer observer){
        if(this.users.containsKey(name)){
            User user = this.users.get(name);
            user.setActive(false);
            user.getUserState().removeObserver(observer);
        }
    }


    //classe che permette di registrarsi
    synchronized public void register (String name,String password)throws HomonymyException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if(this.users.containsKey(name)){
                throw new HomonymyException();
            }
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
            this.users.put(name,user);
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

    public User findUser(String name) throws UserNotExistentException {
        User user = this.users.get(name);
        if(user != null){
            return this.users.get(name);
        }
        throw new UserNotExistentException();
    }

    public User getUser(String name){
        return this.users.get(name);
    }

    // Useful for testing
    protected int getUsersListSize() { return this.users.size(); }
    protected void clearUserList() {
        this.users.clear();
    }
}
