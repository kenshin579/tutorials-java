package com.java.examples;

public class Mimic extends Object implements Cloneable {
    private int id;
    private String name;

    public Mimic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Mimic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
