package it.polimi.ingsw.model.PlayerPackage;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static it.polimi.ingsw.model.PlayerPackage.State.HASSETADICESTATE;
import static it.polimi.ingsw.model.PlayerPackage.State.MUSTPASSTURNSTATE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class PlayerStateTest {

    private PlayerState playerState;
    private Observer mockObserver;

    @Before
    public void before() throws RemoteException {
        playerState = new PlayerState();
        mockObserver = mock(ObserverView.class);

        doThrow(RemoteException.class).when(mockObserver).update(playerState, null);
    }

    @Test (expected = NotAllowedActionException.class)
    public void checkActionNotAllowed() throws NotAllowedActionException {
        playerState.updateState(HASSETADICESTATE);
        playerState.checkAction(TurnActions.SETDICE);
    }

    @Test (expected = NoActionAllowedException.class)
    public void getActionsExcpetion() throws NoActionAllowedException {
        playerState.getActions();
    }

    @Test
    public void getActions() throws NoActionAllowedException {
        playerState.updateState(MUSTPASSTURNSTATE);
        assertEquals(State.MUSTPASSTURNSTATE, playerState.getState());
        assertEquals("- passturn\n", playerState.getActions());
    }


    @Test (expected = RemoteException.class)
    public void notifyObservers() throws RemoteException {
        playerState.addObserver(mockObserver);
        playerState.notifyObservers();
    }

}