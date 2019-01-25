package others.more.abstractAnimals;

public abstract class Pet<T extends Pet<T>> {
    private String name;

    private String eyeColor;
    private int hungryLevel;

    protected abstract T getThis(); //child 객체를 반환하게 되어 있음

    public T setName(String name) {
        this.name = name;
        return getThis();
    }

    public T setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
        return getThis();
    }

    public T setHungryLevel(int hungryLevel) {
        this.hungryLevel = hungryLevel;
        return getThis();
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hungryLevel=" + hungryLevel +
                '}';
    }
}