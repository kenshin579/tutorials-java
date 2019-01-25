//package com.java.examples;
//
//import org.junit.Test;
//
//import java.util.Arrays;
//
//public class JEPTest {
//
//    /**
//     * Local-Variable Syntax for Lambda Parameters (JEP 323)
//     * - JDK 10에서 var가 도입되었지만, 암묵적 타입의 람다 표현식에는 사용할 수 없었음.
//     * ㅁ. (var a) -> a < 5 <— 가능해짐
//     */
//    @Test
//    public void test_JEP323() {
//        var xs = new int[]{3, 2, 6, 4, 8, 9};
//        int x = Arrays
//                .stream(xs)
//                .filter((var a) -> a < 5)
//                .sum();
//        System.out.println(x);
//    }
//}