package simple.methodChain;

public class Pet {
    private String name;
    private String eyeColor;
    private int hungryLevel;

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public Pet setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public Pet setHungryLevel(int hungryLevel) {
        this.hungryLevel = hungryLevel;
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + "Pet{" +
                "name='" + name + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hungryLevel=" + hungryLevel +
                '}';
    }
}