package it.polimi.ingsw.model.Exceptions;

public class DrawException extends Exception {
    private static final String msg = "C'è stato un pareggio: \n";
    private int pointsofthedraw;
    public DrawException() {
        super(msg);
    }
    public DrawException(String msg){ super(msg); }
    public int getPointsofthedraw(){
        return this.pointsofthedraw;
    }

    public DrawException(int points, String message){
        super(message);
        this.pointsofthedraw = points;
    }

    public static String getMsg() {
        return msg;
    }
}

