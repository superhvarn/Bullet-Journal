package cs3500.pa05.model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents our model implementation of our journal
 */
public class JournalModelImpl implements JournalModel {
  private ArrayList<Event> events;
  private ArrayList<ArrayList<Task>> tasks;

  private Theme theme;

  private Event[][] calenderBoard = new Event[7][11];

  /**
   * populates this task list
   */
  public JournalModelImpl() {
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      this.tasks.add(new ArrayList<>());
    }
  }

  /**
   * updates these events
   *
   * @param event to be updated
   * @return
   */
  @Override
  public boolean updateEvents(Event event) {

    for (double i = event.getStartTime(); i < event.getDuration(); i++) {
      if (calenderBoard[event.getDay().getPlacement()][(int) i] != null) {
        return false;
      }
      calenderBoard[event.getDay().getPlacement()][(int) i] = event;
    }
    this.events.add(event);
    return true;
  }

  /**
   * updates these tasks
   *
   * @param task to be updated
   *
   * @return if these tasks should be updated
   */
  @Override
  public boolean updateTasks(Task task) {
    if (!(tasks.get(task.getDay().getPlacement()).size() == 5)) {
      this.tasks.get(task.getDay().getPlacement()).add(task);
      return true;
    }
    return false;
  }

  /**
   * displays task
   *
   * @param dayOfTheWeek the day of the task
   * @param i which position in the journal
   *
   * @return displayed task
   */
  @Override
  public String displayTask(Day dayOfTheWeek, int i) {
    if (i < tasks.get(dayOfTheWeek.getPlacement()).size()) {
      Task task = tasks.get(dayOfTheWeek.getPlacement()).get(i);
      String display = task.getName() + ", " + task.getDay().toString()
          + ", " + task.getCompletionStatus() + ", " + task.getDescription();
      return display;
    } else {
      return "";
    }
  }

  /**
   * getter for events
   *
   * @return a list of events
   */
  @Override
  public ArrayList<Event> getEvents() {
    return this.events;
  }

  /**
   * getter for theme of journal
   *
   * @return this theme
   */
  @Override
  public Theme getTheme() {
    return this.theme;
  }

  /**
   * updates this theme
   *
   * @param theme to be updated to
   */
  public void updateTheme(Theme theme) {
    this.theme = theme;
  }

  /**
   * finds this task by using given task
   *
   * @param day of task
   * @param task to be found
   *
   * @return position of task to be found
   */
  @Override
  public int findTask(Day day, Task task) {
    for (int i = 0; i < tasks.get(day.getPlacement()).size(); i++) {
      if (task == tasks.get(day.getPlacement()).get(i)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * displays total number of events
   *
   * @return the total number of events
   */
  @Override
  public String displayTotalEvents() {
    return "" + this.events.size();
  }

  /**
   * displays total number of tasks
   *
   * @return the total number of tasks
   */
  @Override
  public String displayTotalTasks() {
    int count = 0;
    for (int i = 0; i < tasks.size(); i++) {
      for (int j = 0; j < tasks.get(i).size(); j++) {
        count++;
      }
    }
    return "" + count;
  }

  /**
   * displays percentage of completed tasks
   *
   * @return percentage of completed tasks
   */
  @Override
  public String displayPercent() {
    double count = 0;
    double countCompleted = 0;
    for (int i = 0; i < tasks.size(); i++) {
      for (int j = 0; j < tasks.get(i).size(); j++) {
        count++;
        if (tasks.get(i).get(j).getCompletionStatus().equals("completed")) {
          countCompleted++;
        }
      }
    }
    if (countCompleted == 0) {
      return "0%";
    }
    return new DecimalFormat("#.##").format((countCompleted / count) * 100.0) + "%";
  }

  /**
   * getter for completed tasks
   *
   * @param dayOfTheWeek day the task is assigned to
   *
   * @return number of completed tasks
   */
  @Override
  public int getTasksCompleted(Day dayOfTheWeek) {
    ArrayList<Task> tasksForDay = tasks.get(dayOfTheWeek.getPlacement());
    int count  = 0;
    for (int i = 0; i < tasksForDay.size(); i++) {
      if (!tasksForDay.get(i).getCompletionStatus().equals("completed")) {
        count++;
      }
    }
    return count;
  }

  /**
   *
   * @param dayOfTheWeek day of the week in journal
   *
   * @return percentage of completed tasks for a day
   */
  @Override
  public double getPercentDay(Day dayOfTheWeek) {
    return 1 - ((double) this.getTasksCompleted(dayOfTheWeek)
        / (double) tasks.get(dayOfTheWeek.getPlacement()).size());
  }

  /**
   * to find task by name search
   *
   * @param day in journal
   * @param name of task
   *
   * @return position of found task
   */
  @Override
  public int searchByName(Day day, String name) {
    ArrayList<Task> taskForDay = tasks.get(day.getPlacement());
    for (int i = 0; i < taskForDay.size(); i++) {
      if (taskForDay.get(i).getName().equals(name)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * used to display event
   *
   * @param day of event
   * @param time of event
   *
   * @return event to be displayed
   */
  @Override
  public String displayEvent(String day, String time) {
    for (int i = 0; i < events.size(); i++) {
      if (events.get(i).getDay().toString().equals(day)
          && events.get(i).getStartTime() == Integer.parseInt(time)) {
        return events.get(i).getName() + events.get(i).getDescription()
            + events.get(i).getDay() + events.get(i).getStartTime() + events.get(i).getDuration();
      }
    }
    return "";
  }

  /**
   * getter for task
   *
   * @param day of task
   * @param i position of task in journal
   *
   * @return this task
   */
  @Override
  public Task getTask(Day day, int i) {
    if (i < tasks.get(day.getPlacement()).size()) {
      return tasks.get(day.getPlacement()).get(i);
    } else {
      return new Task("", "", Day.EMPTY, false);
    }
  }

  /**
   * to get list of non-empty tasks
   *
   * @return list of non-empty tasks
   */
  @Override
  public ArrayList<Task> nonEmptyTasks() {
    ArrayList<Task> nonEmptyTasks = new ArrayList<>();
    for (int i = 0; i < tasks.size(); i++) {
      for (int j = 0; j < tasks.size(); j++) {
        if (!this.displayTask(Day.fromPlacement(i), j).equals("")) {
          nonEmptyTasks.add(tasks.get(i).get(j));
        }
      }
    }
    return nonEmptyTasks;
  }

  /**
   * getter for events
   *
   * @param startTime of event
   * @param day of event
   *
   * @return this event
   */
  @Override
  public Event getEvent(String startTime, String day) {
    for (int i = 0; i < events.size(); i++) {
      if (events.get(i).getDay().equals(Day.fromString(day))
          && events.get(i).getStartTime() == Integer.parseInt(startTime)) {
        return events.get(i);
      }
    }
    return new Event("", "", Day.EMPTY, -1, -1);
  }

  @Override
  public ArrayList<ArrayList<Task>> getTasks() {
    return this.tasks;
  }
}
