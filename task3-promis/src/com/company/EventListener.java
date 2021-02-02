package com.company;

public interface EventListener {
    default void asyncTask() throws InterruptedException {
    }
}
