package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents journal viewer for java journal application
 */
public interface JournalView {

  /**
   * Loads the scene of this program
   *
   * @return the scene of java journal application
   *
   * @throws IllegalStateException correct exception if scene is in an illegal state
   */
  Scene load() throws IllegalStateException;
}
