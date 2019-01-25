package ex3;

public class ChangeProcessingHandlerRefactored {
	public void triggerProcessingChangeOf(Code code, Change change) {
		verifyChangeOf(code, change);
		change.accept(new ChangeProcessor(code));
	}

	private void verifyChangeOf(Code code, Change change) {

	}
}
