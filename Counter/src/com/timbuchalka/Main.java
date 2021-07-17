package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
	// use a count down object to count down to 0.
        CountDown countdown1 = new CountDown();

        CountDownThread t1 = new CountDownThread(countdown1);
        t1.setName("Thread 1");

        CountDownThread t2 = new CountDownThread(countdown1);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }

}



class CountDown {
    private int i;

    public void doCountdown() {
        String color;

        switch(Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;

            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;

            default:
                color = ThreadColor.ANSI_GREEN;
        }
        synchronized(color) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }

    }

}

class CountDownThread extends Thread {
    private CountDown threadCountDown;

    public CountDownThread(CountDown countdown) {
        threadCountDown = countdown;
    }

    public void run() {
        threadCountDown.doCountdown();
    }

}
