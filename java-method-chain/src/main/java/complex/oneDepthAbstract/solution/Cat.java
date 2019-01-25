package complex.oneDepthAbstract.solution;

public class Cat extends Pet<Cat> {
    private int awesomeLevel;
    private int cutenessLevel;

    @Override
    protected Cat getThis() {
        return this;
    }

    public Cat setAwesomeLevel(final int awesomeLevel) {
        this.awesomeLevel = awesomeLevel;
        return getThis();
    }

    public Cat setCutenessLevel(final int cutenessLevel) {
        this.cutenessLevel = cutenessLevel;
        return getThis();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + super.toString() + " Cat{" +
                "awesomeLevel=" + awesomeLevel +
                ", cutenessLevel=" + cutenessLevel +
                '}';
    }
}
