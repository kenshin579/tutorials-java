package com.java.examples.javaex1;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * hashCode는 왜 필요한가?
 * <p/>
 * hashCode 구현이 없고 containsKey를 사용해서 같은 객체가 있는지 확인하는 과정에서의 문제점
 * <p/>
 * 참고: http://whiteship.me/?tag=hashcode
 *
 * @author kenshin579
 */
public class ObjectCompareTestDriver {

    public static void main(String[] args) {
        Map<UserSession, Integer> map = new HashMap<UserSession, Integer>();

        UserSession us1 = new UserSession("1", "abc");
        UserSession us2 = new UserSession("2", "def");
        UserSession us3 = new UserSession("3", "rfg");

        map.put(us1, new Integer(1));
        map.put(us2, new Integer(2));
        map.put(us3, new Integer(3));

        UserSession usTest = new UserSession("1", "abc"); // us1 == us4 같아야함
        System.out.println("usTest -> " + usTest);

        boolean intheMap = map.containsKey(usTest);
        System.out.format("is usTest in the map? %s\n", intheMap == true ? "YES" : "NO");

        System.out.println();
        System.out.println("Map List: ");
        Set<UserSession> usersession = map.keySet();
        for (UserSession id : usersession) {
            System.out.format("uid:%s vkey:%s\n", id.getUid(), id.getVkey());
        }
    }

}
