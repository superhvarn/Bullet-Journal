package cs3500.pa05.model;

/**
 * Represents an event
 */
public class Event {
  private String name;
  private String description;
  private Day day;
  private double startTime;
  private double duration;

  /**
   * Constructor
   *
   * @param name name of event
   * @param description description of event
   * @param day day of event
   * @param startTime start time of event
   * @param duration duration of event
   */
  public Event(String name, String description, Day day, double startTime, double duration) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Constructor
   *
   * @param name name of event
   * @param day day of event
   * @param startTime start time of event
   * @param duration duration of event
   */
  Event(String name, Day day, double startTime, double duration) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * getter for day of event
   *
   * @return day of event
   */
  public Day getDay() {
    return day;
  }

  /**
   * getter for start time of event
   *
   * @return start time of event
   */
  public double getStartTime() {
    return startTime;
  }

  /**
   * getter for duration of event
   *
   * @return duration of event
   */
  public double getDuration() {
    return duration;
  }

  /**
   * getter for name of event
   *
   * @return name of event
   */
  public String getName() {
    return name;
  }

  /**
   * getter for description of event
   *
   * @return description of event
   */
  public String getDescription() {
    return description;
  }

  /**
   * updates the name of an event
   *
   * @param name the name of the event to be updated
   */
  public void updateName(String name) {
    this.name = name;
  }

  /**
   * updates the description of an event
   *
   * @param description the description of the event to be updated
   */
  public void updateDescription(String description) {
    this.description = description;
  }

  /**
   * updates the day of an event
   *
   * @param day the day of the event to be updated
   */
  public void updateDay(String day) {
    this.day = Day.fromString(day);
  }

  /**
   * updates the duration of an event
   *
   * @param duration the duration of the event to be updated
   */
  public void updateDuration(String duration) {
    this.duration = Double.parseDouble(duration);
  }

  /**
   * updates the start time of an event
   *
   * @param startTime the start time of the event to be updated
   */
  public void updateStartTime(String startTime) {
    this.startTime = Double.parseDouble(startTime);
  }
}
