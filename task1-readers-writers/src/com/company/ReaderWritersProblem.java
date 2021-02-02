package com.company;

import java.util.concurrent.Semaphore;

public class ReaderWritersProblem {

    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);

    static int readCount = 0;

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Write write = new Write();

        Thread t1 = new Thread(read);
        t1.setName("1(read)");

        Thread x = new Thread(write);
        x.setName("x(write)");

        Thread t2 = new Thread(read);
        t2.setName("2(read)");

        Thread y = new Thread(write);
        y.setName("y(write)");

        Thread t3 = new Thread(read);
        t3.setName("3(read)");

        t1.start();
        x.start();
        t3.start();
        t2.start();
        y.start();
    }

    static class Read implements Runnable {
        @Override
        public void run() {
            try {
                readLock.acquire(); //blokada read
                readCount++;
                if (readCount == 1) {
                    writeLock.acquire(); //blokada write do momentu aż będzie dostępne lub zostanie przerwane
                }
                readLock.release(); // zezwala na wykonanie

                //Reading section
                System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
                //currentThread() -> zwraca wykonywany wątek

                Thread.sleep(1500);

                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

                //Releasing section
                readLock.acquire();
                readCount--;
                if(readCount == 0) {
                    writeLock.release();
                }
                readLock.release();
            }  catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable {
        @Override
        public void run() {
            try {
                writeLock.acquire();
                System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");

                Thread.sleep(2500);

                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
                writeLock.release();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
