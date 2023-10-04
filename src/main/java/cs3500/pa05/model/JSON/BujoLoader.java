package cs3500.pa05.model.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a loader for bujo files
 */
public class BujoLoader {

  private final String directory =
      "/Users/harish/IdeaProjects/lab01-superhvarn/src/main/java/cs3500"
      + "/pa05-aoun-worshippers-1/src/Bujo/";
  private final JournalModel model;
  private String fileContents;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Constructor
   *
   * @param bujoFileDirectory directory of bujo file
   * @param model of application
   *
   * @throws IOException correct exception if error is given
   */
  public BujoLoader(String bujoFileDirectory, JournalModel model) throws IOException {
    File bujoFile = new File(directory + bujoFileDirectory);
    this.fileContents = Files.readString(bujoFile.toPath());
    this.model = model;
  }

  /**
   * updates tasks with Json
   *
   * @throws IOException correct exception if IO exception is thrown
   */
  public void updateTasks() throws IOException {
    final JsonNode jsonContents = mapper.readValue(this.fileContents, JsonNode.class);
    List<Task> tasks = this.convertToTask(jsonContents.get("tasks"));
    for (int i = 0; i < tasks.size(); i++) {
      model.updateTasks(tasks.get(i));
    }
  }

  /**
   * updates theme with Json
   *
   * @throws JsonProcessingException correct exception if there is issue with Json processing
   */
  public void updateTheme() throws JsonProcessingException {
    final JsonNode jsonContents = mapper.readValue(this.fileContents, JsonNode.class);
    Theme theme = mapper.convertValue(jsonContents.get("theme"), Theme.class);
    model.updateTheme(theme);
  }

  /**
   * updates events with Json
   *
   * @throws IOException correct exception if IO exception is thrown
   */
  public void updateEvents() throws IOException {
    final JsonNode jsonContents = mapper.readValue(this.fileContents, JsonNode.class);
    List<Event> events = this.convertToEvent(jsonContents.get("events"));
    for (int i = 0; i < events.size(); i++) {
      model.updateEvents(events.get(i));
    }
  }

  /**
   * Converts a list of events to a list of eventJson
   *
   * @param events to be converted
   *
   * @return list of events
   *
   * @throws IOException correct exception if IO exception is thrown
   */
  private List<Event> convertToEvent(JsonNode events) throws IOException {
    ArrayList<Event> eventList = new ArrayList<>();
    List<EventJson> eventJson = mapper.readValue(
        mapper.treeAsTokens(events),
        new TypeReference<>() {
        }
    );
    for (int i = 0; i < eventJson.size(); i++) {
      eventList.add(new Event(eventJson.get(i).name(), eventJson.get(i).desc(),
          Day.fromString(eventJson.get(i).day().dayOfWeek()), eventJson.get(i).duration(),
          eventJson.get(i).startTime()));
    }
    return eventList;
  }

  /**
   * Converts a list of tasks to a list of taskJson
   *
   * @param tasks to be converted
   *
   * @return list of tasks
   *
   * @throws IOException correct exception if IO exception is thrown
   */
  private List<Task> convertToTask(JsonNode tasks) throws IOException {
    ArrayList<Task> taskList = new ArrayList<>();
    System.out.println(tasks);
    List<TaskJson> taskJson =  mapper.readValue(
        mapper.treeAsTokens(tasks),
        new TypeReference<>() {}
    );
    for (int i = 0; i < taskJson.size(); i++) {
      taskList.add(new Task(taskJson.get(i).name(), taskJson.get(i).desc(),
          Day.fromString(taskJson.get(i).day().dayOfWeek()), taskJson.get(i).completed()));
    }
    return taskList;
  }


}