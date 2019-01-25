package complex.twoDepthAbstract.problem;

public class PersianCat extends Cat<PersianCat> {

    public PersianCat() {
        this.setName("PersianCat");
    }

    @Override
    protected PersianCat getThis() {
        return this;
    }
}
