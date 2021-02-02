package com.company;

public class Promis extends Thread{
    private static int counterThreats = 0;

    private ThenClass mListener; // listener field

    // setting the listener
    public void registerEventListener(ThenClass mListener) {
        this.mListener = mListener;
    }

    // Asynchronous task
    public void then() {
            // Async task always executes in new thread
            new Thread(new Runnable() {

                @Override
                public void run() {
                    counterThreats++;
                    // perform any operation
                    System.out.println("promis" + " " + counterThreats);

                    // invoke the callback method of class ThenClass
                    if (mListener != null) {
                        mListener.asyncTask();
                    }
                }
            }).start();
    }
}