package com.example.bottomnavigationwithfragment.juso;

public class JusoItem {
    String mainJuso;
    String subJuso;

    public String getMainJuso() {
        return mainJuso;
    }

    public void setMainJuso(String mainJuso) {
        this.mainJuso = mainJuso;
    }

    public String getSubJuso() {
        return subJuso;
    }

    public void setSubJuso(String subJuso) {
        this.subJuso = subJuso;
    }

    @Override
    public String toString() {
        return "JusoItem{" +
                "mainJuso='" + mainJuso + '\'' +
                ", subJuso='" + subJuso + '\'' +
                '}';
    }

    public JusoItem(String mainJuso, String subJuso) {
        this.mainJuso = mainJuso;
        this.subJuso = subJuso;
    }
}
