package kr.pe.advenoh.examples;

public class MyClass2 {
    private String str;

    MyClass2() {
//        System.load("some.dll");
        str = "loaded";
    }

    MyClass2(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    static String hello(String arg) {
        return "Hello " + arg;
    }

}