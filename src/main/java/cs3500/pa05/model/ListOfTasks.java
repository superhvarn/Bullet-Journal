package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class ListOfTasks {

  private ArrayList<Task> tasks = new ArrayList<>();

  /**
   * a list of tasks
   */
  public ListOfTasks() {

  }

  /**
   * updates this task with given task
   *
   * @param task to be used to update
   */
  public void updateTasks(Task task) {
    tasks.add(task);
  }
}
