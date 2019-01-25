package others.more.abstractAnimals;

public class BullDog extends Dog<BullDog> {

    public BullDog() {
        this.setName("BullDog");
    }

    @Override
    protected BullDog getThis() {
        return this;
    }
}

