package com.java.examples.safeVarArgs;

import java.util.ArrayList;
import java.util.List;

public class SafeVarArgsEx {
    public static void main(String[] args) {
        SafeVarArgsEx safeVarArgsEx = new SafeVarArgsEx();
        List<String> list = new ArrayList<>();
        list.add("Frank");
        list.add("Angela");
        list.add("David");
        safeVarArgsEx.print(list);
    }

    @SafeVarargs
    private final void print(List... names) {
        for (List name : names) {
            System.out.println(name);
        }
    }
}
