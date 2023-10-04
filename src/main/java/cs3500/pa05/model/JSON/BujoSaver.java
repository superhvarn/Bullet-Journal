package cs3500.pa05.model.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a saver for bujo files
 */
public class BujoSaver {
  private final String directory =
      "/Users/harish/IdeaProjects/lab01-superhvarn/src/main/java/cs3500"
      + "/pa05-aoun-worshippers-1/src/Bujo/";
  private JournalModel model;

  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Constructor
   *
   * @param model of application
   */
  public BujoSaver(JournalModel model) {
    this.model = model;
  }

  /**
   * writes to given file
   *
   * @param text name of place we want to write to
   *
   * @throws IOException correct exception if IO error occurs
   */
  public void writeToFile(String text) throws IOException {

    FileWriter writer = null;
    try {
      writer = new FileWriter(Path.of(directory + text).toFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ArrayList<ArrayList<Task>> tasks = model.getTasks();
    ArrayList<Event> events = model.getEvents();

    ArrayList<TaskJson> taskJsons = new ArrayList<>();
    ArrayList<EventJson> eventJsons = new ArrayList<>();

    for (int i = 0; i < tasks.size(); i++) {
      for (int j = 0; j < tasks.get(i).size(); j++) {
        taskJsons.add(this.taskToJson(tasks.get(i).get(j)));
      }
    }

    for (int i = 0; i < events.size(); i++) {
      eventJsons.add(this.eventToJson(events.get(i)));
    }

    BulletJSON bullet = new BulletJSON(taskJsons, eventJsons, model.getTheme());

    mapper.writeValue(Path.of(directory + text).toFile(), bullet);

    writer.close();
  }

  /**
   * Converts an event to a eventJson
   *
   * @param event to be converted
   *
   * @return eventJson
   */
  public EventJson eventToJson(Event event) {
    EventJson eventJson = new EventJson(event.getName(), event.getDescription(),
        new DayJson(event.getDay().toString(), event.getDay().getPlacement()),
        event.getStartTime(), event.getDuration());
    return eventJson;
  }

  /**
   * Converts a task to a taskJson
   *
   * @param task to be converted
   *
   * @return taskJson
   */
  public TaskJson taskToJson(Task task) {
    boolean completed = false;
    if (task.getCompletionStatus().equals("completed")) {
      completed = true;
    }
    TaskJson taskNode = new TaskJson(task.getName(), task.getDescription(),
        new DayJson(task.getDay().toString(), task.getDay().getPlacement()), completed);
    return taskNode;
  }
}
