package com.example.user.androidproject;

/**
 * Created by user on 2016-12-06.
 */
public class CommonData{

    public static int[] check = new int[41];

    public static int getCheck(int i) {
        return check[i];
    }

    public static void setCheck(int i, int j) {
        CommonData.check[i] = j;
    }
}