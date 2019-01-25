package ex3;

public interface Visitator {
	void visit(Refactoring refactoring);

	void visit(Improvement improvement);

	void visit(Growth growth);
}
