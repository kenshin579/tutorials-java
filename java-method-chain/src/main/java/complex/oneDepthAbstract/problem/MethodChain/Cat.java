package complex.oneDepthAbstract.problem.MethodChain;

public class Cat extends Pet {
    private int awesomeLevel;
    private int cutenessLevel;

    public Cat catchMice() {
        System.out.println("I caught a mouse!");
        return this;
    }

    public Cat setAwesomeLevel(final int awesomeLevel) {
        this.awesomeLevel = awesomeLevel;
        return this;
    }

    public Cat setCutenessLevel(final int cutenessLevel) {
        this.cutenessLevel = cutenessLevel;
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + super.toString() + " Cat{" +
                "awesomeLevel=" + awesomeLevel +
                ", cutenessLevel=" + cutenessLevel +
                '}';
    }
}
