package com.java.examples;

/**
 * clone()은 메모리 차원의 자료 복사임
 * - protected native Object clone(): 자바가 아닌 다른 언어(C, C++)로 작성이 되었음을 명시함
 * - Object로부터 상속 받은 클래스에서 clone() 메서드를 사용하려면 Cloneable 인터페이스가 반드시 필요함
 * -
 * <p/>
 * 참고: http://ifyouprogrammer.tistory.com/27
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Mimic mimicObj = new Mimic(1000, "호랑이");
        Mimic mimicCloneObj = (Mimic) mimicObj.clone(); // 복사를 위한 메서드 사용

        System.out.println("clone 사용");
        System.out.println(mimicObj.hashCode() + " " + mimicObj);
        System.out.println(mimicCloneObj.hashCode() + " " + mimicCloneObj);
        mimicCloneObj.setName("사자");
        System.out.println("name 변경후");
        System.out.println(mimicObj.hashCode() + " " + mimicObj);
        System.out.println(mimicCloneObj.hashCode() + " " + mimicCloneObj);

        // assignment의 경우, 변경시 assign한 obj도 변경이 된다.
        System.out.println("= 사용");

        mimicCloneObj = mimicObj;
        System.out.println(mimicObj.hashCode() + " " + mimicObj);
        System.out.println(mimicCloneObj.hashCode() + " " + mimicCloneObj);
        mimicCloneObj.setName("사자");
        System.out.println("name 변경후");
        System.out.println(mimicObj.hashCode() + " " + mimicObj);
        System.out.println(mimicCloneObj.hashCode() + " " + mimicCloneObj);

    }


}
