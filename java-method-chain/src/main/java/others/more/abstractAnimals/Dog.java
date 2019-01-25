package others.more.abstractAnimals;

public abstract class Dog<T extends Dog<T>> extends Pet<T> {
    private int happinessLevel;

    public T catchMice() {
        System.out.println("I caught a mouse!");
        return getThis();
    }

    public int getAwesomeLevel() {
        return happinessLevel;
    }

    public T setAwesomeLevel(final int happinessLevel) {
        this.happinessLevel = happinessLevel;
        return getThis();
    }

    @Override
    public String toString() {
        return super.toString() + " Cat{" +
                "happinessLevel=" + happinessLevel +
                '}';
    }
}
