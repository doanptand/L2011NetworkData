package com.ddona.data.paser;

public class Director {
    private static Director INSTANCE;


    public static Director getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Director();
        }
        return INSTANCE;
    }

    private Director() {

    }
}
