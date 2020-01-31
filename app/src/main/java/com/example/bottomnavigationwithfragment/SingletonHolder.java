package com.example.bottomnavigationwithfragment;

import java.util.ArrayList;

public class SingletonHolder {
    public ArrayList<StoreItem> favoriteArrayList;


    private SingletonHolder() {
        favoriteArrayList = new ArrayList<>();
        //init

    }


    private static class Singleton {
        private static final SingletonHolder instance = new SingletonHolder();
    }

    public static SingletonHolder getInstance() {
        System.out.println("create instance");
        return Singleton.instance;
    }



}