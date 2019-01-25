package simple.NoMethodChain;

public class Pet {
    private String name;
    private String eyeColor;
    private int hungryLevel;

    public void setName(String name) {
        this.name = name;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHungryLevel(int hungryLevel) {
        this.hungryLevel = hungryLevel;
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