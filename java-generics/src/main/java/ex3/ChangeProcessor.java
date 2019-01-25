package ex3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeProcessor implements Visitator {
	private final Code code;

	public ChangeProcessor(Code code) {
		this.code = code;
	}

	@Override
	public void visit(Refactoring refactoring) {
		log.info("visit");
	}

	@Override
	public void visit(Improvement improvement) {
		log.info("visit");
	}

	@Override
	public void visit(Growth growth) {
		log.info("visit");
	}
}
