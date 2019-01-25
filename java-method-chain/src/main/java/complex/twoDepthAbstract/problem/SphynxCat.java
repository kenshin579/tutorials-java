package complex.twoDepthAbstract.problem;

public class SphynxCat extends Cat<SphynxCat> {

    public SphynxCat() {
        this.setName("SphynxCat");
    }

    @Override
    protected SphynxCat getThis() {
        return this;
    }
}
