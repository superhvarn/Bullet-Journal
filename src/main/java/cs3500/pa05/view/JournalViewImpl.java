package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Representing implemented view of java journal application
 */
public class JournalViewImpl implements JournalView {
  FXMLLoader loader = new FXMLLoader();

  /**
   * Constructor
   *
   * @param controller java journal program controller
   */
  public JournalViewImpl(JournalController controller) {
    this.loader.setLocation(getClass().getClassLoader().getResource("firstPrompt.fxml"));
    this.loader.setController(controller);
  }

  @Override
  public Scene load() throws IllegalStateException {
    try {
      return loader.load();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
