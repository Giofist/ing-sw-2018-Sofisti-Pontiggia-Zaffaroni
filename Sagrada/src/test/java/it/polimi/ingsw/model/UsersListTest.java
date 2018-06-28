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

    private Observer mockObserver;

    @Before
    public void before() throws IOException {
        mockObserver = mock(Observer.class);
    }


    @Test
    public void registerTest() throws UserNotExistentException, HomonymyException {
        UsersList.Singleton().clearUserList();
        assertEquals(0, UsersList.Singleton().getUsersListSize());
        UsersList.Singleton().register("Utente", "pass");

        assertEquals(1, UsersList.Singleton().getUsersListSize());
        assertEquals("Utente", UsersList.Singleton().findUser("Utente").getName());
        assertEquals("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1",
                UsersList.Singleton().findUser("Utente").getPassword());
    }



    @Test
    public void getUserTest() throws UserNotExistentException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        User user = UsersList.Singleton().findUser("Utente");
        assertEquals("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", user.getPassword());

    }

    @Test (expected = UserNotExistentException.class)
    public void getUserExceptionTest() throws UserNotExistentException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        User user = UsersList.Singleton().findUser("Utente2");
    }

    @Test
    public void logoutTest() throws UserNotExistentException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        //usersList.logOut("Utente");

        assertFalse(UsersList.Singleton().findUser("Utente").isActive());
    }

    @Test
    public void checkTest() throws LoginException, IsAlreadyActiveException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        UsersList.Singleton().check("Utente", "pass", mockObserver);
    }

    @Test (expected = LoginException.class)
    public void checkLoginWrongUsernameExceptionTest() throws LoginException, IsAlreadyActiveException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        UsersList.Singleton().check("Utentedsad", "pass", mockObserver);
    }

    @Test (expected = LoginException.class)
    public void checkLoginWrongPasswordExceptionTest() throws LoginException, IsAlreadyActiveException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        UsersList.Singleton().check("Utente", "pass1", mockObserver);
    }

    @Test (expected = IsAlreadyActiveException.class)
    public void checkIsAlreadyActiveExceptionTest() throws LoginException, IsAlreadyActiveException, UserNotExistentException, HomonymyException {
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Utente", "pass");
        UsersList.Singleton().findUser("Utente").setActive(true);
        UsersList.Singleton().check("Utente", "pass", mockObserver);
    }

}