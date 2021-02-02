package com.company;

public class Main {

    public static void main(String[] args) {
        Promis x = new Promis();
        ThenClass mListener = new ThenClass();
        x.registerEventListener(mListener);
        x.then();

        Promis y = new Promis();
        ThenClass mLis = new ThenClass();
        x.registerEventListener(mLis);
        x.then();

        Promis z = new Promis();
        ThenClass mList = new ThenClass();
        z.registerEventListener(mList);
        z.then();
    }
}
