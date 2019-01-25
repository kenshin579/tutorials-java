package complex.twoDepthAbstract.solution;

public interface IPet<T> {

    @SuppressWarnings("unchecked")
    default T getThis() {
        return (T) this;
    }
}
