package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for task class
 */
public class TaskTest {

  private Task task;

  /**
   * setup for task
   */
  @BeforeEach
  public void setup() {
    task = new Task("Task1", Day.MONDAY, false);
  }

  /**
   * tests the first shown constructor in Task class
   */
  @Test
  public void testFirstConstructor() {
    assertEquals("Task1", task.getName());
    assertEquals(Day.MONDAY, task.getDay());
    assertEquals("", task.getCompletionStatus());
    assertNull(task.getDescription());
  }

  /**
   * tests that a completion status of a task is updated
   */
  @Test
  public void testUpdateCompletionStatus() {
    task.updateCompletionStatus(true);
    assertEquals("completed", task.getCompletionStatus());
  }

  /**
   * tests that a description of a task is updated
   */
  @Test
  public void testUpdateDescription() {
    task.updateDescription("Updated Description");
    assertEquals("Updated Description", task.getDescription());
  }

  /**
   * tests that a name of a task is updated
   */
  @Test
  public void testUpdateName() {
    task.updateName("Updated Task Name");
    assertEquals("Updated Task Name", task.getName());
  }

  /**
   * tests that a day of a task is updated
   */
  @Test
  public void testUpdateDay() {
    task.updateDay("TUE");
    assertEquals(Day.TUESDAY, task.getDay());
  }
}
