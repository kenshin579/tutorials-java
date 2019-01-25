package ex3;

public class ChangeProcessingHandler {

	public CodeDelta triggerProcessingChangeOf(Code code, Change change) throws UnsuportedChangeException {
		verifyChangeOf(code, change);

		if (change instanceof Refactoring) {
			return processRefactoring(code, (Refactoring) change);
		} else if (change instanceof Improvement) {
			return processImprovement(code, (Improvement) change);
		} else if (change instanceof Growth) {
			return processGrowth(code, (Growth) change);
		} else {
			throw new UnsuportedChangeException();
		}
	}

	private CodeDelta processGrowth(Code code, Growth change) {
		return null;
	}

	private CodeDelta processImprovement(Code code, Improvement change) {
		return null;
	}

	private CodeDelta processRefactoring(Code code, Refactoring change) {
		return null;
	}

	private void verifyChangeOf(Code code, Change change) {

	}
}
