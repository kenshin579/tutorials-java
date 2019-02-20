package in28minutes.data.stub;

import in28minutes.data.api.TodoService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class TodoServiceStub implements TodoService {
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
	}

	@Override public void deleteTodo(String todo) {
		log.info("deleteTodo todo: {}", todo);
	}
}