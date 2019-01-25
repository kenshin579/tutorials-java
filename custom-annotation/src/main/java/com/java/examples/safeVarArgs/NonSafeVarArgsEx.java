package com.java.examples.safeVarArgs;

import java.util.ArrayList;
import java.util.List;

public class NonSafeVarArgsEx {
    public static void main(String[] args) {
        NonSafeVarArgsEx nonSafeVarArgsEx = new NonSafeVarArgsEx();
        List<String> list = new ArrayList<>();
        list.add("Frank");
        list.add("Angela");
        list.add("David");
        nonSafeVarArgsEx.print(list);
    }

    void print(List... names) {
        for (List<String> name : names) {
            System.out.println(name);
        }
    }
}
