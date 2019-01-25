package com.java.examples.util.perf.examples;


import com.java.examples.util.perf.StopWatchAverage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;


public class HashsetTreeSetLinkedHashSet {
    static StopWatchAverage sw1 = new StopWatchAverage("HashSet");
    static StopWatchAverage sw2 = new StopWatchAverage("TreeSet");
    static StopWatchAverage sw3 = new StopWatchAverage("LinkedHashSet");

    public void test1(int runCount) {
        HashSet<String> hs = new HashSet<String>();
        TreeSet<String> ts = new TreeSet<String>();
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();

        String data = "abcdefghijklmnopqrstuvwxyz";

        for (int loop = 0; loop < runCount; loop++) {
            hs.add(data + loop);
            ts.add(data + loop);
            lhs.add(data + loop);
        }

        //Hashset 테스트
        sw1.start();
        Iterator<String> hsIter = hs.iterator();
        while (hsIter.hasNext()) {
            String temp = hsIter.next();
        }
        sw1.stop();

        //TreeSet 테스트
        sw2.start();
        Iterator<String> tsIter = hs.iterator();
        while (tsIter.hasNext()) {
            String temp = tsIter.next();
        }
        sw2.stop();

        //LinkedHashSet 테스트
        sw3.start();
        Iterator<String> lhsIter = hs.iterator();
        while (lhsIter.hasNext()) {
            String temp = lhsIter.next();
        }
        sw3.stop();

    }

    public static void main(String[] args) {
        HashsetTreeSetLinkedHashSet test = new HashsetTreeSetLinkedHashSet();

        for (int loop = 0; loop < 100; loop++) {
            test.test1(10000);
        }

        System.out.println(sw1);
        System.out.println(sw2);
        System.out.println(sw3);
    }

}
