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


    /**
     * Private constructor invoked by the singleton method
     */
    private UsersList(){
        this.users = new Hashtable<>();
    }


    /**
     * Method for the singleton pattern
     * @return The singleton instance of the UsersList
     */
    public static  UsersList Singleton() {
        if (instance == null) {
            instance = new UsersList();
            instance.loadUsersList();
        }
        return instance;
    }


    /**
     * This private method is used to load the list of registered users from a .csv file when the server is started.
     * If for any reason we are not able to read the file an empty list will be created.
     */
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


    /**
     * This method is used to perform the login
     * @param name The username we want to login with
     * @param password The password associated to the account
     * @param observer The client that wants to become an observer
     * @throws it.polimi.ingsw.model.Exceptions.LoginException Exception thrown when the login is not successful
     * @throws IsAlreadyActiveException Exception thrown when the username is already connected to the server
     */
    synchronized public void check( String name, String password, Observer observer)throws it.polimi.ingsw.model.Exceptions.LoginException, IsAlreadyActiveException {
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


    /**
     * This method is used to perform the logout of the user
     * @param name Name of the user that want to log out
     * @param observer The Client observer we want to remove from the list list
     */
     synchronized public void logOut( String name, Observer observer){
        if(this.users.containsKey(name)){
            User user = this.users.get(name);
            user.setActive(false);
            user.getUserState().removeObserver(observer);
        }
    }


    /**
     * @param name The name of the new user we want to register
     * @param password The password of the new user we want to register
     * @throws HomonymyException Exception thrown in case there is already a user registered with the specified name
     */
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


    /**
     * Method used to produce the sha256 of a string
     * @param password The plain text password we want to hash
     * @return The sha256 of the input password
     */
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


    /**
     * @param name Name of the user we want to try to retrieve
     * @return The user corresponding to the specified name
     * @throws UserNotExistentException Exception thrown when the user doesn't exist on the server
     */
    public User findUser(String name) throws UserNotExistentException {
        User user = this.users.get(name);
        if(user != null){
            return this.users.get(name);
        }
        throw new UserNotExistentException();
    }


    /**
     * This method is used for situations where we are sure that the user exists in the server
     * @param name Name of the user we want to retrieve
     * @return The user corresponding to the specified name
     */
    public User getUser(String name){
        return this.users.get(name);
    }

    // Useful for testing
    protected int getUsersListSize() { return this.users.size(); }
    protected void clearUserList() {
        this.users.clear();
    }
}
