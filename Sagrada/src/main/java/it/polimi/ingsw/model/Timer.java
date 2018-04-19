package it.polimi.ingsw.model;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


class Stopwatch {
    static int interval;
    static Timer timer;

    public static void main(String[] args) throws IOException {
        int delay = 1000;
        int period = 1000;
        int sec = setTimer();
        char response;
        timer = new Timer();
        interval = Integer.parseInt(String.valueOf(sec));

        System.out.println("Are you ready? Y/N");
        response = (char) System.in.read();
        if((response == 'Y') || (response == 'y')) {
            startTimer(delay, period, sec);
        }
        else System.out.println("Oh, no!");
    }

    private static final int setInterval() {
        //interval=forceStart();
        if (interval == 1) {
            timer.cancel();
            System.out.println("The game starts!");
        }
        return --interval;
    }

    private static int setTimer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input waiting time => : (Type s to Start)");
        String sec = sc.nextLine();
        return Integer.parseInt(sec);
    }

    public static void startTimer(int delay, int period, int sec) {
        System.out.println(sec);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);
    }

    /*public static int forceStart() {
        char start;
        Scanner scanner = new Scanner(System.in);
        start = (char) scanner.nextInt();
        if ((start == 'S') || (start == 's')) {
            return 0;
        }
    }


    /*
    public void stopTimer() {
        // TODO implement here
    }

    public void addTime() {
        // TODO implement here
    }

}
    */
}

