package others.more.interfaceAnimal;

public abstract class Cat<T extends Cat<T>> extends Pet<T> {
    private int awesomeLevel;
    private int cutenessLevel;

    public T catchMice() {
        System.out.println("I caught a mouse!");
        return getThis();
    }

    public int getAwesomeLevel() {
        return awesomeLevel;
    }

    public T setAwesomeLevel(final int awesomeLevel) {
        this.awesomeLevel = awesomeLevel;
        return getThis();
    }

    public T setCutenessLevel(final int cutenessLevel) {
        this.cutenessLevel = cutenessLevel;
        return getThis();
    }

    @Override
    public String toString() {
        return super.toString() + " Cat{" +
                "awesomeLevel=" + awesomeLevel +
                ", cutenessLevel=" + cutenessLevel +
                '}';
    }
}
