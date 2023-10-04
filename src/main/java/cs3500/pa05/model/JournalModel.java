package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Represents our java journal model (Model in MVC)
 */
public interface JournalModel {

  /**
   * Updates an event in the journal
   *
   * @param event to be updated
   * @return
   */
  boolean updateEvents(Event event);

  /**
   * Updates a task in the journal
   *
   * @param task to be updated
   *
   * @return if task to be updated
   */
  boolean updateTasks(Task task);

  /**
   * Displays our task
   *
   * @param dayOfTheWeek the day of the task
   * @param i which position in the journal
   *
   * @return a task to be viewed
   */
  String displayTask(Day dayOfTheWeek, int i);

  /**
   * getter for tasks
   *
   * @return a 2D list of tasks
   */
  ArrayList<ArrayList<Task>> getTasks();

  /**
   * getter for events
   *
   * @return a list of events
   */
  ArrayList<Event> getEvents();

  /**
   * getter for theme of journal
   *
   * @return this theme
   */
  Theme getTheme();

  /**
   * updates the theme of journal
   *
   * @param theme chosen theme
   */
  void updateTheme(Theme theme);

  /**
   * used to find a task
   *
   * @param day of task
   * @param task to be found
   *
   * @return placement of task in journal
   */
  int findTask(Day day, Task task);

  /**
   * used to display the total amount of events in journal
   *
   * @return the amount of events in journal
   */
  String displayTotalEvents();

  /**
   * used to display the total amount of tasks in journal
   *
   * @return the amount of tasks in journal
   */
  String displayTotalTasks();

  /**
   * used to display the percentage of completed tasks in journal
   *
   * @return the percentage of completed tasks in journal
   */
  String displayPercent();

  /**
   * getter for completed tasks
   *
   * @param dayOfTheWeek day the task is assigned to
   *
   * @return the number of completed tasks
   */
  int getTasksCompleted(Day dayOfTheWeek);

  /**
   * to get the percentage amount done in week in journal
   *
   * @param dayOfTheWeek day of the week in journal
   *
   * @return percentage of done amount
   */
  double getPercentDay(Day dayOfTheWeek);

  /**
   * used to search a task by its name
   *
   * @param day in journal
   * @param name of task
   *
   * @return position of task
   */
  int searchByName(Day day, String name);

  /**
   * to display events
   *
   * @param day of event
   * @param time of event
   *
   * @return the event to be displayed
   */
  String displayEvent(String day, String time);

  /**
   * getter for tasks
   *
   * @param day of task
   * @param i position of task in journal
   *
   * @return this task at given day and position
   */
  Task getTask(Day day, int i);

  /**
   * list of non-empty tasks
   *
   * @return list of non-empty tasks
   */
  ArrayList<Task> nonEmptyTasks();

  /**
   * getter for events
   *
   * @param startTime of event
   * @param day of event
   *
   * @return this event on given day and start time
   */
  Event getEvent(String startTime, String day);
}
