package cs3500.pa05;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.JournalControllerImpl;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.JournalModelImpl;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.JournalViewImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents our java journal
 */
public class Driver extends Application {

  /**
   * starts up our java journal application
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   *
   * @throws IOException throws the correct exception when an IO exception occurs
   */
  @Override
  public void start(Stage stage) throws IOException  {
    stage.setTitle("Java Journal");
    JournalModel board = new JournalModelImpl();
    JournalController loader = new JournalControllerImpl(board, stage);
    JournalView swgv = new JournalViewImpl(loader);
    Scene scene = swgv.load();
    try {
      // load and place the view's scene onto the stage
      stage.setScene(scene);
      loader.run();

      // render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Entry point for program
   *
   * @param args arguments for program
   */
  public static void main(String[] args) {
    launch();
  }

}
