package it.polimi.ingsw.model.Exceptions;

public class RoundTrackException extends Exception {
    private static final String msg = "Qualche problema sul Tracciato Round\n";
    public RoundTrackException() {
        super(msg);
    }
    public RoundTrackException(String msg){ super(msg); }
}
