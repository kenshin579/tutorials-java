package com.java.examples.javaex2;


import java.util.HashMap;
import java.util.Map;


/**
 * hashCode는 왜 필요한가?
 * - hashCode 구현없이 map에 같은 객체를 넣었을때의 문제
 *
 * @author kenshin579
 */
public class TestDriver {

    public static void main(String[] arg) {
        Map<Person, Integer> map = new HashMap<Person, Integer>();

        map.put(new Person("test"), 20);
        map.put(new Person("test"), 20);

        System.out.println("Map size: " + map.size() + " (should be 1!!)");
    }

}
