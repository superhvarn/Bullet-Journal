package cs3500.pa05.model;

/**
 * Represents a theme of our java journal
 */
public enum Theme {

  /**
   * Red color theme
   */
  RED("JavaJournalRED.fxml"),

  /**
   * blue color theme
   */
  BLUE("JavaJournalBLUE.fxml"),

  /**
   * default color theme
   */
  DEFAULT("JavaJournal.fxml");

  private String path;

  /**
   * Constructor
   *
   * @param path of a theme
   */
  Theme(String path) {
    this.path = path;
  }

  /**
   * makes theme to string
   *
   * @return stringed theme enum
   */
  public String toString() {
    return this.path;
  }
}
