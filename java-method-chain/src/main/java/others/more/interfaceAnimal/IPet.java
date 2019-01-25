package others.more.interfaceAnimal;

public interface IPet<T> {
    default T getThis() {
        return (T) this;
    }
}
