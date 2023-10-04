package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.JournalModel;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents implement task popups
 */
public class TaskPopupImpl implements TaskPopup {
  private Popup popup;

  private TextFlow task1;

  private TextFlow task2;

  private TextFlow task3;

  private TextFlow task4;

  private TextFlow task5;

  @FXML
  private Button close = new Button("close");

  private Button day;

  private Stage stage;

  private JournalModel model;

  private Day dayOfTheWeek;

  private TextFlow link1 = new TextFlow();
  private TextFlow link2 = new TextFlow();
  private TextFlow link3 = new TextFlow();
  private TextFlow link4 = new TextFlow();
  private TextFlow link5 = new TextFlow();
  private java.awt.Desktop Desktop;

  /**
   * Constructor
   *
   * @param day button in place of specific day
   * @param stage of application
   * @param model of program
   * @param dayOfTheWeek which day task is
   */
  TaskPopupImpl(Button day, Stage stage, JournalModel model, Day dayOfTheWeek) {
    this.day = day;
    this.stage = stage;
    this.model = model;
    this.dayOfTheWeek = dayOfTheWeek;
    this.popup = new Popup();
  }

  @FXML
  void handleTasks() throws IOException {
    System.out.println("hi");

    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("TaskAbstract.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    popup.getContent().add((Node) s.getRoot());

    day.setOnAction(event -> {
      this.updateLabels();
      makePopup(popup);
    });

    close.setOnAction(event -> {
      popup.hide();
      popup.getContent().clear();
    });
  }

  @FXML
  private void makePopup(Popup popup) {
    popup.show(this.stage, 500, 400);
  }

  void updateLabels() {

    Label tasksCompleted = new Label("Tasks remaining: " + model.getTasksCompleted(dayOfTheWeek));
    ProgressBar progressBar = new ProgressBar();
    progressBar.setProgress(model.getPercentDay(dayOfTheWeek));
    this.checkLink(model.getTask(dayOfTheWeek, 0).getDescription(), link1);
    this.checkLink(model.getTask(dayOfTheWeek, 1).getDescription(), link2);
    this.checkLink(model.getTask(dayOfTheWeek, 2).getDescription(), link3);
    Label label4 = new Label(model.getTask(dayOfTheWeek, 3).getName()
        + model.getTask(dayOfTheWeek, 3).getDay()
        + model.getTask(dayOfTheWeek, 3).getCompletionStatus());
    this.checkLink(model.getTask(dayOfTheWeek, 3).getDescription(), link4);
    Label label5 = new Label(model.getTask(dayOfTheWeek, 4).getName()
        + model.getTask(dayOfTheWeek, 4).getDay()
        + model.getTask(dayOfTheWeek, 4).getCompletionStatus());
    this.checkLink(model.getTask(dayOfTheWeek, 4).getDescription(), link5);
    Label label1 = new Label(model.getTask(dayOfTheWeek, 0).getName()
        + model.getTask(dayOfTheWeek, 0).getDay()
        + model.getTask(dayOfTheWeek, 0).getCompletionStatus());
    task1 = new TextFlow(label1, link1);
    Label label2 = new Label(model.getTask(dayOfTheWeek, 1).getName()
        + model.getTask(dayOfTheWeek, 1).getDay()
        + model.getTask(dayOfTheWeek, 1).getCompletionStatus());
    task2 = new TextFlow(label2, link2);
    Label label3 = new Label(model.getTask(dayOfTheWeek, 2).getName()
        + model.getTask(dayOfTheWeek, 2).getDay()
        + model.getTask(dayOfTheWeek, 2).getCompletionStatus());
    task3 = new TextFlow(label3, link3);
    task4 = new TextFlow(label4, link4);
    task5 = new TextFlow(label5, link5);

    VBox vbox = new VBox(); // Create a VBox layout container
    vbox.getChildren().addAll(close, tasksCompleted, progressBar,
        task1, task2, task3, task4, task5); // Add labels to VBox vertically

    popup.getContent().add(vbox); // Add VBox to popup
  }

  /**
   * Checks if link is valid
   *
   * @param link to be validated
   * @param hyperlink string is converted to
   */
  public void checkLink(String link, TextFlow hyperlink) {
    Hyperlink hyplink = new Hyperlink();
    boolean append = false;
    String validLink = "";
    String[] potentialLinks = link.split(" ");
    for (int i = 0; i < potentialLinks.length; i++) {
      if (this.isValidLink(potentialLinks[i])) {

        hyplink.setText(potentialLinks[i].toString());
        hyperlink.getChildren().add(hyplink);
        validLink = potentialLinks[i];
      } else {
        hyperlink.getChildren().add(new Label(potentialLinks[i]));
      }
    }
    String finalValidLink = validLink;
    hyplink.setOnAction(event ->
    {
      try {
        Desktop.getDesktop().browse(new URL(finalValidLink).toURI());
      } catch (IOException e) {
        e.printStackTrace();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Checks if a link is valid
   *
   * @param link to check if a string is a link
   *
   * @return whether the link is valid or not
   */
  public boolean isValidLink(String link) {
    try {
      (new java.net.URL(link)).openStream().close();
      return true;
    } catch (MalformedURLException ignored) {
    } catch (IOException e) {
      return false;
    }
    return false;
  }
}
