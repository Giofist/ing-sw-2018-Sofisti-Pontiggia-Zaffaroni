package it.polimi.ingsw.model.PlayerPackage;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;
import it.polimi.ingsw.model.PlayerState;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.model.TurnActions;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import static it.polimi.ingsw.model.State.HASSETADICESTATE;
import static it.polimi.ingsw.model.State.MUSTPASSTURNSTATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        playerState.setState(HASSETADICESTATE);
        playerState.checkAction(TurnActions.SETDICE);
    }

    @Test
    public void getActions() throws NoActionAllowedException {
        // No actions available
        List<TurnActions> actions = new LinkedList<>();
        actions = playerState.getActions();
        assertEquals(0, actions.size());

        // 1 action available
        playerState.setState(MUSTPASSTURNSTATE);
        assertEquals(State.MUSTPASSTURNSTATE, playerState.getState());
        actions = playerState.getActions();
        assertEquals(TurnActions.LEAVEMATCH, actions.get(0));
        assertEquals(TurnActions.PASSTURN, actions.get(1));
    }


    @Test (expected = RemoteException.class)
    public void notifyObservers() throws RemoteException {
        playerState.addObserver(mockObserver);
        playerState.notifyObservers();
    }

}