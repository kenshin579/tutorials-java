package ex3;

public class Refactoring implements Change {
	@Override
	public void accept(Visitator visitator) {
		visitator.visit(this);
	}
}
