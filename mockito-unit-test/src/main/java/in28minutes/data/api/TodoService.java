package in28minutes.data.api;

import java.util.List;

public interface TodoService {
	List<String> retrieveTodos(String user);
}