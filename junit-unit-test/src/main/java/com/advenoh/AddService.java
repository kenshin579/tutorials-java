package com.advenoh;

public class AddService {

    public int add(int x, int y) {
        return x + y;
    }

    public void print(int x, int y) {
        System.out.println("result = " + add(x, y));
    }
}
