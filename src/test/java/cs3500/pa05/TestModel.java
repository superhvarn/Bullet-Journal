package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.JournalModelImpl;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Represents file visitor tests
 *
 */
class TestModel {

  private static JournalModel journalModel;

  @BeforeAll
  public static void setUp() {
    journalModel = new JournalModelImpl();
  }

  @Test
  public void testUpdateEvents() {
    Event event = new Event("Event 1", "Description 1",
        Day.MONDAY, 1, 2);
    journalModel.updateEvents(event);
    ArrayList<Event> events = journalModel.getEvents();
    assertEquals(1, events.size());
    assertEquals(event, events.get(0));
  }

  @Test
  public void testUpdateTasks() {
    Task task = new Task("Task 1", "Description 1", Day.FRIDAY, false);
    assertTrue(journalModel.updateTasks(task));
    ArrayList<ArrayList<Task>> tasks = journalModel.getTasks();
    assertEquals(1, tasks.get(Day.FRIDAY.getPlacement()).size());
    assertEquals(task, tasks.get(Day.FRIDAY.getPlacement()).get(0));

    Task task1 = new Task("Task " + 6, "Description " + 6, Day.MONDAY, false);
    assertTrue(journalModel.updateTasks(task1));


    journalModel.updateTasks(new Task("", "", Day.MONDAY, false));
    journalModel.updateTasks(new Task("", "", Day.MONDAY, false));
    journalModel.updateTasks(new Task("", "", Day.MONDAY, false));
    journalModel.updateTasks(new Task("", "", Day.MONDAY, false));
    journalModel.updateTasks(new Task("", "", Day.MONDAY, false));
    Task task2 = new Task("Task 6", "Description 6", Day.MONDAY, false);
    assertFalse(journalModel.updateTasks(task2));
  }

  @Test
  public void testDisplayTask() {
    journalModel = new JournalModelImpl();
    Task task1 = new Task("Task 1", "Description 1", Day.MONDAY, false);
    Task task2 = new Task("Task 2", "Description 2", Day.MONDAY, true);
    journalModel.updateTasks(task1);
    journalModel.updateTasks(task2);
    assertEquals("Task 1, MON, , Description 1", journalModel.displayTask(
        Day.MONDAY, 0));
    assertEquals("Task 2, MON, completed, Description 2",
        journalModel.displayTask(Day.MONDAY, 1));
    assertEquals("", journalModel.displayTask(Day.MONDAY, 2));
  }

  @Test
  public void testGetTheme() {
    journalModel.updateTheme(Theme.BLUE);
    assertEquals(Theme.BLUE, journalModel.getTheme());
  }

  @Test
  public void testDisplayTotalEvents() {
    journalModel = new JournalModelImpl();
    assertEquals("0", journalModel.displayTotalEvents());
  }

  @Test
  public void testGetPercentDay() {
    journalModel = new JournalModelImpl();
    journalModel.updateTasks(new Task("", "", Day.FRIDAY, false));
    assertEquals(0.0, journalModel.getPercentDay(Day.FRIDAY));
    journalModel.updateTasks(new Task("", "", Day.FRIDAY, true));
    assertEquals(0.5, journalModel.getPercentDay(Day.FRIDAY));
  }

  @Test
  public void testGetTask() {
    journalModel = new JournalModelImpl();
    Task task = new Task("hi", "", Day.FRIDAY, false);
    journalModel.updateTasks(task);
    assertEquals(task,
        journalModel.getTask(Day.FRIDAY, journalModel.findTask(Day.FRIDAY, task)));
    assertEquals(0, journalModel.searchByName(Day.FRIDAY, "hi"));
    assertEquals("1", journalModel.displayTotalTasks());
    assertEquals(new ArrayList<>(List.of(task)), journalModel.nonEmptyTasks());
  }

  @Test
  public void testFromString() {
    assertEquals(Day.SUNDAY, Day.fromString("SUN"));
    assertEquals(Day.MONDAY, Day.fromString("MON"));
    assertEquals(Day.TUESDAY, Day.fromString("TUE"));

    assertThrows(IllegalArgumentException.class, () -> Day.fromString("Not a day"));
  }

  @Test
  public void testFromPlacementException() {

    assertThrows(IllegalArgumentException.class, () -> Day.fromPlacement(-2));
    assertThrows(IllegalArgumentException.class, () -> Day.fromPlacement(7));
  }

  @Test
  public void testGetEvent() {
    Event testEvent = journalModel.getEvent("-4", "MON");
    assertEquals("", testEvent.getName());
  }

  @Test
  public void testDisplayPercent() {
    String percent = journalModel.displayPercent();
    assertEquals("0%", percent);
  }

  @Test
  public void testDisplayEvent() {
    String eventDetails = journalModel.displayEvent("FLU", "300");
    assertEquals("", eventDetails);
  }

  @Test
  public void testToString() {
    assertEquals("JavaJournalRED.fxml", Theme.RED.toString(),
        "Red theme path should match");
    assertEquals("JavaJournalBLUE.fxml", Theme.BLUE.toString(),
        "Blue theme path should match");
    assertEquals("JavaJournal.fxml", Theme.DEFAULT.toString(),
        "Default theme path should match");
  }

  @Test
  void displayEventWhenEventExists() {
    Event event = new Event("Meeting", "Meeting with team", Day.MONDAY, 10, 1);
    journalModel.updateEvents(event);

    String expected = "";
    String actual = journalModel.displayEvent("MONDAY", "10");

    assertEquals(expected, actual,
        "The displayed event information should match the added event");
  }

  @Test
  void displayEventWhenEventDoesNotExist() {
    String actual = journalModel.displayEvent("MONDAY", "10");
    assertEquals("", actual,
        "When no matching event is found, an empty string should be returned");
  }

  @Test
  void displayEventDayAndTimeMatch() {
    Event event = new Event("Meeting", "Meeting with team", Day.MONDAY, 10, 1);
    journalModel.updateEvents(event);

    String actual = journalModel.displayEvent("MON", "10");

    String expected = event.getName() + event.getDescription() + event.getDay()
        + event.getStartTime() + event.getDuration();

    assertEquals(expected, actual,
        "When both day and start time match, "
            + "it should return a string representation of the event");
  }

  @Test
  public void testEventGetter() {
    Event testEvent = new Event("TestEvent", "This is a test event",
        Day.MONDAY, 8, 2);
    journalModel.updateEvents(testEvent);

    Event retrievedEvent = journalModel.getEvent("8", "MON");
    assertEquals(testEvent, retrievedEvent, "Event retrieved should match the one added");
  }

  @Test
  public void testDisplayPercentSpecific() {
    Task task1 = new Task("Task 1", "Description 1", Day.MONDAY, false);
    Task task2 = new Task("Task 2", "Description 2", Day.MONDAY, true);
    journalModel.updateTasks(task1);
    journalModel.updateTasks(task2);

    String result = journalModel.displayPercent();
    assertEquals("50%", result);
  }

  @Test
  public void testFindTaskFailure() {
    JournalModelImpl journal = new JournalModelImpl();
    Task task1 = new Task("Task 1", "Description 1", Day.MONDAY, false);
    Task task2 = new Task("Task 2", "Description 2", Day.MONDAY, false);
    journal.updateTasks(task1);

    int taskIndex = journal.findTask(Day.MONDAY, task2);
    assertEquals(-1, taskIndex);
  }
}
