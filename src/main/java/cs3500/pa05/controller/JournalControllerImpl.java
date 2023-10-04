package cs3500.pa05.controller;

import cs3500.pa05.model.JSON.BujoLoader;
import cs3500.pa05.model.JSON.BujoSaver;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents controller for java journal application
 */
public class JournalControllerImpl implements JournalController {

  @FXML
  private Button EventButton = new Button();

  private Popup eventPopup;

  private Label[][] labels = new Label[12][7];

  private Popup taskPopup;

  private Stage stage;

  @FXML
  private GridPane calender = new GridPane();

  @FXML
  private TextField eventName = new TextField();

  @FXML
  private TextField taskName = new TextField();

  private Theme theme;
  @FXML
  private TextField eventDescription = new TextField();

  @FXML
  private TextField taskDescription = new TextField();

  @FXML
  private CheckBox completedTask = new CheckBox();

  @FXML
  private TextField startTime = new TextField();

  @FXML
  private TextField eventDuration = new TextField();

  @FXML
  private TextField eventDay = new TextField();

  @FXML
  private TextField taskDay = new TextField();

  @FXML
  private Button TaskButton = new Button();
  private JournalModel model;

  @FXML
  private Button sunTasks = new Button();

  @FXML
  private Button monTasks = new Button();

  @FXML
  private Button tueTasks = new Button();

  @FXML
  private TextField password;

  @FXML
  private Button wedTasks = new Button();

  @FXML
  private Button thuTasks = new Button();

  @FXML
  private Button friTasks = new Button();

  @FXML
  private Button satTasks = new Button();

  private Popup tasksForDay;

  @FXML
  private Button save = new Button();
  private Popup savePopup;

  @FXML
  private TextField file;

  @FXML
  private Button open = new Button();

  @FXML
  private Button create = new Button();
  private Popup openPopup;

  @FXML
  private TextField openFile;

  @FXML
  private CheckBox red = new CheckBox();

  @FXML
  private CheckBox blue = new CheckBox();

  @FXML
  private CheckBox green = new CheckBox();

  @FXML
  private VBox sidebar;


  private Popup warningPopup;

  @FXML
  private Label totalEvents;

  @FXML
  private Label totalTasks;

  @FXML
  private Label percent;

  private PauseTransition splashTransition = new PauseTransition();

  @FXML
  private TextField eventEditName;

  @FXML
  private TextField eventEditDescription;

  @FXML
  private TextField eventEditDay;

  @FXML
  private TextField eventDurationEdit;

  @FXML
  private TextField startTimeEdit;

  @FXML
  private Button taskOpener = new Button();

  @FXML
  private Button eventEdit = new Button();

  @FXML
  private Button eventOpener = new Button();

  private Popup taskDisplay;

  private Popup eventDisplay;

  @FXML
  private TextField taskNameOpen;

  @FXML
  private TextField taskDayOpen;

  @FXML
  private TextField eventDayOpen;

  @FXML
  private TextField eventStartOpen;

  @FXML
  private Button taskEditButton = new Button();

  private Popup taskEditDialog = new Popup();

  @FXML
  private TextField taskEditName = new TextField();

  private Popup eventEditPopup = new Popup();

  @FXML
  private TextField taskEditDescription = new TextField();

  @FXML
  private TextField taskEditDay = new TextField();

  @FXML
  private CheckBox completedTaskEdit = new CheckBox();

  /**
   * Constructor
   *
   * @param board java journal "board" (week view)
   * @param stage where javaFX is built upon and scene of our journal exists
   */
  public JournalControllerImpl(JournalModel board, Stage stage) {
    this.model = Objects.requireNonNull(board);
    this.stage = stage;
  }

  /**
   * runs this java journal application
   * 
   * @throws IOException correct exception if IO error occurs
   */
  @Override
  @FXML
  public void run() throws IOException {
    eventEditPopupHandler();
    handleEditEvent();
    openTaskEdit();
    openEventPopup();
    openTaskPopup();
    eventOpenerHandler();
    taskOpenerHandler();
    themeHandler();
    createHandler();
    openHandler();
    submitBujo();
    initGrid();
    Button eventT = new Button("Submit");
    eventT.setOnAction(event -> {
      taskPopup.hide();
      boolean completedBoolean = false;
      if (this.completedTask.isSelected()) {
        completedBoolean = true;
      }
      Task currentTask = new Task(taskName.getText(), taskDescription.getText(),
          Day.fromString(taskDay.getText()), completedBoolean);
      if (!model.updateTasks(currentTask)) {
        makePopup(warningPopup);
      } else {
        Label label = new Label(model.displayTask(currentTask.getDay(),
            model.findTask(currentTask.getDay(), currentTask)));
        label.wrapTextProperty();
        sidebar.getChildren().add(label);
        taskTotalHandler();
        percentHandler();
      }
    });
    taskHandler();
    eventHandler();
    warningHandler();
    this.eventPopup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("EventPrompt.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    eventPopup.getContent().add(s.getRoot());
    Button eventB = new Button("Submit");
    eventPopup.getContent().add(eventB);
    eventB.setOnAction(event -> {
      eventPopup.hide();
      String eventName = this.eventName.getText();
      String eventDescription = this.eventDescription.getText();
      Day eventDay = Day.fromString(this.eventDay.getText());
      int startTime = Integer.parseInt(this.startTime.getText());
      int duration = Integer.parseInt(this.eventDuration.getText());
      this.eventName.clear();
      this.eventDescription.clear();
      this.eventDay.clear();
      this.eventDuration.clear();
      this.startTime.clear();
      Event currentEvent = new Event(eventName, eventDescription, eventDay, startTime, duration);
      if (!model.updateEvents(currentEvent) || startTime > 9) {
        makePopup(warningPopup);
      }
      else {
        TextFlow hyperlink = new TextFlow();
        this.checkLink(currentEvent.getDescription(), hyperlink);

        for (int i = 14; i < calender.getChildren().size(); i++) {
          if (calender.getChildren().get(i) instanceof TextFlow) {
            TextFlow textFlow = (TextFlow) calender.getChildren().get(i);
            Label label = new Label();
            if ((((i - 1) % 7) == currentEvent.getDay().getPlacement())
                && ((i - 1) / 7 - 3 == currentEvent.getStartTime())) {
              textFlow.getChildren().clear();
              label.setText(currentEvent.getName() + " " + currentEvent.getDuration() + " "
                  + " " + currentEvent.getStartTime());
              label.wrapTextProperty();
              textFlow.getChildren().addAll(label, hyperlink);

            }
          }
        }
        eventTotalHandler();
      }
    });
    TaskPopupImpl sunPop = new TaskPopupImpl(sunTasks, stage, model, Day.SUNDAY);
    TaskPopupImpl monPop = new TaskPopupImpl(monTasks, stage, model, Day.MONDAY);
    TaskPopupImpl tuePop = new TaskPopupImpl(tueTasks, stage, model, Day.TUESDAY);
    TaskPopupImpl wedPop = new TaskPopupImpl(wedTasks, stage, model, Day.WEDNESDAY);
    TaskPopupImpl thuPop = new TaskPopupImpl(thuTasks, stage, model, Day.THURSDAY);
    TaskPopupImpl friPop = new TaskPopupImpl(friTasks, stage, model, Day.FRIDAY);
    TaskPopupImpl satPop = new TaskPopupImpl(satTasks, stage, model, Day.SATURDAY);

    try {
      sunPop.handleTasks();
      monPop.handleTasks();
      tuePop.handleTasks();
      wedPop.handleTasks();
      thuPop.handleTasks();
      friPop.handleTasks();
      satPop.handleTasks();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    this.taskPopup = new Popup();
    FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("TaskPrompt.fxml"));
    loader2.setController(this);
    Scene taskS = loader2.load();
    taskPopup.getContent().add((Node) taskS.getRoot());
    taskPopup.getContent().add((Node) eventT);

    this.handleEdit();

    this.handleSave();
    submitSave();
  }

  private void splashTransitionHandler() {
    if (password.getText().equals("HarishVaradarajan")) {
      splashTransition.play();
      splashTransition.setOnFinished(event -> {
        FXMLLoader journalLoader =
            new FXMLLoader(getClass().getClassLoader().getResource(model.getTheme().toString()));
        journalLoader.setController(this);
        Scene journalScene = null;
        try {
          journalScene = journalLoader.load();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        this.stage.setScene(journalScene);
        try {
          run();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

  /**
   * Allows for editing events through popup
   *
   * @throws IOException correct exception if IO error occurs
   */
  private void eventEditPopupHandler() throws IOException {
    VBox vbox = new VBox();
    Label label = new Label("Add a space and then put your changes for each component");
    this.eventEditPopup = new Popup();
    Button eventEditB = new Button("Edit");
    FXMLLoader loader3 = new FXMLLoader(getClass().getClassLoader().getResource("EventEdit.fxml"));
    loader3.setController(this);
    Scene taskEdit = loader3.load();
    vbox.getChildren().addAll(label, eventEditB);
    eventEditPopup.getContent().add((Node) taskEdit.getRoot());
    eventEditPopup.getContent().add((Node) vbox);
    eventEditB.setOnAction(event -> {
      eventEditPopup.hide();
      String[] names = eventEditName.getText().split(" ");
      String[] descriptions = eventEditDescription.getText().split(" ");
      String[] days = eventEditDay.getText().split(" ");
      String[] startTimes = startTimeEdit.getText().split(" ");
      String[] durations = eventDurationEdit.getText().split(" ");
      Event editedEvent = model.getEvent(startTimes[0], days[0]);
      editedEvent.updateName(names[1]);
      editedEvent.updateDescription(descriptions[1]);
      editedEvent.updateDay(days[1]);
      editedEvent.updateDuration(durations[1]);
      editedEvent.updateStartTime(startTimes[1]);
      this.initGrid();
    });
  }

  /**
   * handles the editing of an event
   */
  private void handleEditEvent() {
    eventEdit.setOnAction(event -> {
      this.makePopup(eventEditPopup);
    });
  }

  /**
   * handles the editing of a task
   */
  private void handleEditTask() {
    List<Node> sidebarChildren = sidebar.getChildren();
    ArrayList<Task> nonEmptyTasks = model.nonEmptyTasks();
    for (int i = 2; i < sidebarChildren.size(); i++) {
      sidebarChildren.remove(i);
    }
    for (int i = 0; i < nonEmptyTasks.size(); i++) {
      Label label = new Label();
      label.setText(nonEmptyTasks.get(i).getName() + ", "
          + nonEmptyTasks.get(i).getCompletionStatus() + ", "
          + nonEmptyTasks.get(i).getDay() + ", "
          + nonEmptyTasks.get(i).getDescription()
          + ", ");
      sidebarChildren.add(label);
    }
    percentHandler();
  }

  /**
   * opens the popup to edit tasks
   */
  private void openTaskEdit() {
    taskEditButton.setOnAction(event -> {
      makePopup(taskEditDialog);
    });
  }

  /**
   * Checks if link is valid
   *
   * @param link to be validated
   * @param hyperlink string is converted to
   */
  @Override
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
  @Override
  public boolean isValidLink(String link) {
    try {
      (new java.net.URL(link)).openStream().close();
      return true;
    } catch (MalformedURLException e) {
    } catch (IOException e) {
      return false;
    }
    return false;
  }


  /**
   * handles the editing of a popup
   *
   * @throws IOException throws correct exception if IO error occurs
   */
  private void handleEdit() throws IOException {
    VBox vbox = new VBox();
    Label label = new Label("Add a space and then put your changes for each component");
    this.taskEditDialog = new Popup();
    Button taskEditB = new Button("Edit");
    FXMLLoader loader3 = new FXMLLoader(getClass().getClassLoader().getResource("TaskEdit.fxml"));
    loader3.setController(this);
    Scene taskEdit = loader3.load();
    vbox.getChildren().addAll(label, taskEditB);
    taskEditDialog.getContent().add((Node) taskEdit.getRoot());
    taskEditDialog.getContent().add((Node) vbox);
    taskEditB.setOnAction(event -> {
      taskEditDialog.hide();
      boolean completedBoolean = false;
      if (this.completedTaskEdit.isSelected()) {
        completedBoolean = true;
      }
      String[] names = taskEditName.getText().split(" ");
      String[] descriptions = taskEditDescription.getText().split(" ");
      String[] days = taskEditDay.getText().split(" ");
      int edit = model.searchByName(Day.fromString(days[0]), names[0]);
      Task editedTask = model.getTask(Day.fromString(days[0]), edit);
      editedTask.updateName(names[1]);
      editedTask.updateDescription(descriptions[1]);
      editedTask.updateDay(days[1]);
      editedTask.updateCompletionStatus(completedBoolean);
      this.handleEditTask();
    });
  }

  /**
   * opens the event popup to enter event in journal
   *
   * @throws IOException throws correct exception if IO error occurs
   */
  private void openEventPopup() throws IOException {
    VBox eventViewBox = new VBox();
    Button submitOpenEvent = new Button("Submit");
    Button exitOpenEvent = new Button("Exit");
    eventViewBox.getChildren().addAll(submitOpenEvent, exitOpenEvent);
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("OpenEventPrompt.fxml"));
    loader.setController(this);
    Scene eventOpen = loader.load();
    eventDisplay = new Popup();
    eventDisplay.getContent().add((Node) eventOpen.getRoot());
    eventDisplay.getContent().add((Node) eventViewBox);
    exitOpenEvent.setOnAction(event -> {
      eventDisplay.hide();
    });
    submitOpenEvent.setOnAction(event -> {
      Label label = new Label(model.displayEvent(eventDayOpen.getText(), eventStartOpen.getText()));
      eventViewBox.getChildren().add(label);
    });
  }

  private void openTaskPopup() throws IOException {
    VBox taskViewBox = new VBox();
    Button submitOpenTask = new Button("Submit");
    Button exitOpenTask = new Button("Exit");
    taskViewBox.getChildren().addAll(submitOpenTask, exitOpenTask);
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
            .getResource("OpenTaskPrompt.fxml"));
    loader.setController(this);
    Scene taskOpen = loader.load();
    taskDisplay = new Popup();
    taskDisplay.getContent().add((Node) taskOpen.getRoot());
    taskDisplay.getContent().add((Node) taskViewBox);
    exitOpenTask.setOnAction(event -> {
      taskDisplay.hide();
    });
    submitOpenTask.setOnAction(event -> {
      Label label = new Label(model.displayTask(Day.fromString(taskDayOpen.getText()),
          model.searchByName(Day.fromString(taskDayOpen.getText()), taskNameOpen.getText())));
      label.setPrefHeight(100);
      label.wrapTextProperty();
      taskViewBox.getChildren().add(label);
    });
  }

  /**
   * handles the opening of event popups
   */
  private void eventOpenerHandler() {
    eventOpener.setOnAction(event -> {
      makePopup(eventDisplay);
    });
  }

  /**
   * handles the opening of task popups
   */
  private void taskOpenerHandler() {
    taskOpener.setOnAction(event -> {
      makePopup(taskDisplay);
    });
  }

  /**
   * handles the displaying of percent completion in journal
   */
  private void percentHandler() {
    percent.setText("Tasks Completed: " + model.displayPercent());
  }

  /**
   * handles the displaying of total number of tasks
   */
  private void taskTotalHandler() {
    totalTasks.setText("Total Tasks: " + model.displayTotalTasks());
  }

  /**
   * handles the displaying of total number of events
   */
  private void eventTotalHandler() {
    totalEvents.setText("Total Events" + model.displayTotalEvents());
  }

  /**
   * handles the theme of journal
   */
  private void themeHandler() {
    red.setOnAction(event -> {
      this.theme = Theme.RED;
      model.updateTheme(theme);
    });
    green.setOnAction(event -> {
      this.theme = Theme.DEFAULT;
      model.updateTheme(theme);
    });
    blue.setOnAction(event -> {
      this.theme = Theme.BLUE;
      model.updateTheme(theme);
    });
  }

  /**
   * handles the commitment warning popup in journal
   *
   * @throws IOException throws correct exception if IO error occurs
   */
  private void warningHandler() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("Warning.fxml"));
    loader.setController(this);
    Scene taskS = loader.load();
    warningPopup = new Popup();
    warningPopup.getContent().add((Node) taskS.getRoot());
    Button exitW = new Button("Exit");
    warningPopup.getContent().add((Node) exitW);
    exitW.setOnAction(event -> {
      warningPopup.hide();
    });
  }

  /**
   * handles the submitting of a bujo file popup in journal
   *
   * @throws IOException throws correct exception if IO error occurs
   */
  private void submitBujo() throws IOException {
    openPopup = new Popup();
    Button openB = new Button("Confirm");
    openB.setPrefWidth(10);
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("OpenPrompt.fxml"));
    loader.setController(this);
    Scene openS = loader.load();
    openPopup.getContent().add((Node) openS.getRoot());
    openPopup.getContent().add((Node) openB);
    openB.setOnAction(event -> {
      openPopup.hide();
      try {
        BujoLoader bujoLoader = new BujoLoader(openFile.getText(), model);
        bujoLoader.updateTasks();
        bujoLoader.updateEvents();
        bujoLoader.updateTheme();
        FXMLLoader journalLoader =
            new FXMLLoader(getClass().getClassLoader().getResource("SplashScreen.fxml"));
        journalLoader.setController(this);
        Scene journalScene = journalLoader.load();
        this.stage.setScene(journalScene);
        splashTransition = new PauseTransition(new Duration(2000));
        splashTransitionHandler();
        System.out.println("running");
        this.run();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }


  /**
   * handles the creation of application
   */
  private void createHandler() {
      create.setOnAction(event -> {
        FXMLLoader journalLoader =
            new FXMLLoader(getClass().getClassLoader().getResource("SplashScreen.fxml"));
        journalLoader.setController(this);
        Scene journalScene = null;
        try {
          journalScene = journalLoader.load();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        this.stage.setScene(journalScene);
        splashTransition = new PauseTransition(new Duration(2000));
        this.splashTransitionHandler();
        try {
          this.run();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
  }

  /**
   * handles the opening of popups
   */
  private void openHandler() {
    open.setOnAction(event -> {
      makePopup(openPopup);
    });
  }

  /**
   * handles the popup feature of saving a bujo file
   *
   * @throws IOException throws correct exception if IO error occurs
   */
  private void submitSave() throws IOException {
    Button saveB = new Button("Confirm");
    saveB.setPrefWidth(10);
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SavePopup.fxml"));
    loader.setController(this);
    Scene taskS = loader.load();
    savePopup = new Popup();
    savePopup.getContent().add((Node) taskS.getRoot());
    savePopup.getContent().add((Node) saveB);
    saveB.setOnAction(event -> {
      savePopup.hide();
      BujoSaver saver = new BujoSaver(model);
      try {
        saver.writeToFile(file.getText());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  /**
   * handles the saving popup for bujo files
   */
  private void handleSave() {
    save.setOnAction(event -> {
      makePopup(savePopup);
    });
  }

  // Modify the initGrid() method

  /**
   * creates initial journal
   */
  void initGrid() {
    for (int i = 14; i < calender.getChildren().size(); i++) {
      if (calender.getChildren().get(i) instanceof TextFlow) {
        TextFlow text = (TextFlow) calender.getChildren().get(i);
        text.getChildren().clear();
      }
    }
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 7; j++) {
        TextFlow textFlow =
            new TextFlow(new Label(model.displayEvent(Day.fromPlacement(j).toString(),
                i + "")));
        calender.add(textFlow, j, i + 2);
      }
    }
  }

  /**
   * handles task popup
   */
  void taskHandler() {
    TaskButton.setOnAction(event -> {
      makePopup(taskPopup);
    });
  }

  /**
   * handles event popup
   */
  void eventHandler() {
    EventButton.setOnAction(event -> {
      makePopup(eventPopup);
    });
  }

  /**
   * creates a generic popup
   *
   * @param popup type of popup needed
   */
  @FXML
  private void makePopup(Popup popup) {
    popup.show(this.stage);
  }
}
