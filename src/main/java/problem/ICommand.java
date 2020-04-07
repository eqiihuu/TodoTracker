package problem.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import problem.model.ToDo.ToDoBuilder;

public interface ICommand {

  void add(Map<String, String> data) throws PlatformRequiredException;

  void update(HashMap<String, String> data) throws IOException;

}
