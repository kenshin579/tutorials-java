package complex.twoDepthAbstract.problem;

public class BullDog extends Dog<BullDog> {

    public BullDog() {
        this.setName("BullDog");
    }

    @Override
    protected BullDog getThis() {
        return this;
    }
}

