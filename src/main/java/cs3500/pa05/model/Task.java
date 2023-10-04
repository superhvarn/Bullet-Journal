package cs3500.pa05.model;

/**
 * Represents a task in the java journal
 */
public class Task {
  private String name;
  private Day dayOfTheWeek;
  private boolean completed;
  private String description;

  Task(String name, Day dayOfTheWeek, boolean completed) {
    this.name = name;
    this.dayOfTheWeek = dayOfTheWeek;
    this.completed = completed;
  }

  /**
   * Constructor
   *
   * @param name of task
   * @param description of task
   * @param dayOfTheWeek of task
   * @param completed if task completed
   */
  public Task(String name, String description, Day dayOfTheWeek, boolean completed) {
    this.name = name;
    this.dayOfTheWeek = dayOfTheWeek;
    this.completed = completed;
    this.description = description;
  }

  /**
   * getter for day of task
   *
   * @return day of task
   */
  public Day getDay() {
    return dayOfTheWeek;
  }

  /**
   * getter for name of task
   *
   * @return name of task
   */
  public String getName() {
    return name;
  }

  /**
   * getter for completion status of task
   *
   * @return completion status of task
   */
  public String getCompletionStatus() {
    if (completed) {
      return "completed";
    } else {
      return "";
    }
  }

  /**
   * getter for description of task
   *
   * @return description of task
   */
  public String getDescription() {
    return description;
  }

  /**
   * updates name of task
   *
   * @param name of task to be updated
   */
  public void updateName(String name) {
    this.name = name;
  }

  /**
   * updates description of task
   *
   * @param description of task to be updated
   */
  public void updateDescription(String description) {
    this.description = description;
  }

  /**
   * updates day of task
   *
   * @param day of task to be updated
   */
  public void updateDay(String day) {
    this.dayOfTheWeek = Day.fromString(day);
  }

  /**
   * updates completion status of task
   *
   * @param completedBoolean status of task to be updated
   */
  public void updateCompletionStatus(boolean completedBoolean) {
    this.completed = completedBoolean;
  }
}
