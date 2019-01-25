package others.more.abstractAnimals;

public class BombayCat extends Cat<BombayCat> {

    public BombayCat() {
        this.setName("BombayCat");
    }

    @Override
    protected BombayCat getThis() {
        return this;
    }
}
