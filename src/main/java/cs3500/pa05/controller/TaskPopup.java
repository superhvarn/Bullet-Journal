package cs3500.pa05.controller;

import javafx.scene.text.TextFlow;

/**
 * Represents a task popup in application
 */
public interface TaskPopup {

  /**
   * determines if link is valid and converts to hyperlink
   *
   * @param link to be validated
   * @param hyperlink string is converted to
   */
  void checkLink(String link, TextFlow hyperlink);

  /**
   * determines if any link is valid
   *
   * @param link to check if a string is a link
   *
   * @return if link is valid
   */
  boolean isValidLink(String link);
}
