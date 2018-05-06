package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class NotNearAnotherDiceException extends TileConstrainException {
    private static final String msg = "Non puoi mettere un dado qui, perchè non ce n'è nessuno vicino";
    public NotNearAnotherDiceException() {
        super(msg);
    }
    public NotNearAnotherDiceException(String msg){ super(msg); }
}
