package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.IsAlreadyActiveException;
import it.polimi.ingsw.model.Exceptions.LoginException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UsersListTest {

    private UsersList usersList;
    private Observer mockObserver;

    @Before
    public void before() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/main/resources/UsersList.txt");
        writer.close();

        usersList = UsersList.Singleton();
        mockObserver = mock(Observer.class);
    }

    @Test
    public void singletonTest() {
        UsersList secondReference = UsersList.Singleton();
        assertTrue(usersList == secondReference);
    }

    @Test
    public void registerTest() throws UserNotExistentException, HomonymyException {
        assertEquals(0, usersList.getUsersListSize());
        usersList.register("Utente", "pass");

        assertEquals(1, usersList.getUsersListSize());
        assertEquals("Utente", usersList.findUser("Utente").getName());
        assertEquals("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1",
                                    usersList.findUser("Utente").getPassword());
    }



    @Test
    public void getUserTest() throws UserNotExistentException, HomonymyException {
        usersList.register("Utente", "pass");
        User user = usersList.findUser("Utente");
        assertEquals("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", user.getPassword());

    }

    @Test (expected = UserNotExistentException.class)
    public void getUserExceptionTest() throws UserNotExistentException, HomonymyException {
        usersList.register("Utente", "pass");
        User user = usersList.findUser("Utente2");
    }

    @Test
    public void logoutTest() throws UserNotExistentException, HomonymyException {
        usersList.register("Utente", "pass");
        //usersList.logOut("Utente");

        assertFalse(usersList.findUser("Utente").isActive());
    }

    @Test
    public void checkTest() throws LoginException, IsAlreadyActiveException, HomonymyException {
        usersList.register("Utente", "pass");
        usersList.check("Utente", "pass", mockObserver);
    }

    @Test (expected = LoginException.class)
    public void checkLoginWrongUsernameExceptionTest() throws LoginException, IsAlreadyActiveException, HomonymyException {
        usersList.register("Utente", "pass");
        usersList.check("Utentedsad", "pass", mockObserver);
    }

    @Test (expected = LoginException.class)
    public void checkLoginWrongPasswordExceptionTest() throws LoginException, IsAlreadyActiveException, HomonymyException {
        usersList.register("Utente", "pass");
        usersList.check("Utente", "pass1", mockObserver);
    }

    @Test (expected = IsAlreadyActiveException.class)
    public void checkIsAlreadyActiveExceptionTest() throws LoginException, IsAlreadyActiveException, UserNotExistentException, HomonymyException {
        usersList.register("Utente", "pass");
        usersList.findUser("Utente").setActive(true);
        usersList.check("Utente", "pass", mockObserver);
    }

}