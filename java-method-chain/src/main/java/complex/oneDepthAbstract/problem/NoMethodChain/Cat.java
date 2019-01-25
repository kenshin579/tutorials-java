package complex.oneDepthAbstract.problem.NoMethodChain;

public class Cat extends Pet {
    private int awesomeLevel;
    private int cutenessLevel;

    public void catchMice() {
        System.out.println("I caught a mouse!");
    }

    public void setAwesomeLevel(final int awesomeLevel) {
        this.awesomeLevel = awesomeLevel;
    }

    public void setCutenessLevel(final int cutenessLevel) {
        this.cutenessLevel = cutenessLevel;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + super.toString() + " Cat{" +
                "awesomeLevel=" + awesomeLevel +
                ", cutenessLevel=" + cutenessLevel +
                '}';
    }
}
