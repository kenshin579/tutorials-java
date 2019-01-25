package complex.oneDepthAbstract.solution;

public class Dog extends Pet<Dog> {
    @Override
    protected Dog getThis() {
        return this;
    }

    public void catchFrisbee() {
        System.out.println("I caught a frisbee!");
    }

}
