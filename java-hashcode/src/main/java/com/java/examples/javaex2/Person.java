package com.java.examples.javaex2;

public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return name.equals(((Person) obj).name);
    }
}


