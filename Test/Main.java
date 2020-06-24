import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class Main extends Application {

    
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    // Main Scene of application
    private static Scene mainScene;

    // Root for all the elements
    private static Group menuGroup = new Group();

    // Root for all the elements
    private static Group root = new Group();

    // Window width
    public static final float width = 600f;

    // Window height
    public static final float height = 400f;

    // Quiz loaded
    private static Quiz quiz = null;

    // Correct Answers
    private static int correct;

    // Start quiz
    private static void startQuiz() {
        root.getChildren().clear();

        VBox content = new VBox(5);
        ScrollPane pane = new ScrollPane(content);
        pane.setPrefHeight(height);
        pane.setHbarPolicy(ScrollBarPolicy.NEVER);
        pane.setPrefWidth(width);

        root.getChildren().add(pane);

        ArrayList<Question> questions = quiz.getQuestions();
        Collections.shuffle(questions);

        for (int i = 0; i < questions.size(); i++)
            content.getChildren().addAll(new QuestionNode(questions.get(i), i + 1));

        Button submitQuiz = new Button("Check Results");

        submitQuiz.setOnAction(event -> {
            correct = 0;
            try {
                // Checking the inputs
                for (Node node : content.getChildren()) {
                    if (node instanceof QuestionNode) {
                        QuestionNode temp = (QuestionNode) node;
                        if (temp.answer.getText().equals("") && temp.answerGroup.getSelectedToggle() == null)
                            throw new Exception();
                    }
                }

                content.getChildren().forEach(item -> {
                    if (item instanceof QuestionNode) {
                        QuestionNode temp = (QuestionNode) item;

                        if (temp.checkAnswer()) {
                            correct++;
                            temp.isCorrect.setStyle("-fx-border-color: green;");
                        } else
                            temp.isCorrect.setStyle("-fx-border-color: red;");
                    }
                });

                Alert results = new Alert(AlertType.CONFIRMATION);
                results.setTitle("Results");
                results.setHeaderText(String.format("You answered %d out of %d problems", correct, questions.size()));
                results.setContentText(
                        "If you want to redo the quiz press the ok button, you can finish the quiz by pressing cancel button");
                if (results.showAndWait().get() == ButtonType.CANCEL) {
                    root.getChildren().clear();
                    root.getChildren().add(menuGroup);
                }

            } catch (Exception e) {
                Alert warning = new Alert(AlertType.WARNING);
                warning.setTitle("Warning");
                warning.setHeaderText("Not all the problems were answered");
                warning.setContentText("You should answer all the problems in the list");
                warning.showAndWait();
            }
        });

        content.getChildren().add(submitQuiz);
    }

    // Application setup
    @Override
    public void start(Stage stage) throws Exception {
        Text welcomeText = new Text(width / 3.5, 50, "'Welcome to Quiz'");
        welcomeText.setFont(Font.font("Verdana", 20));
        Button chooseFileBtn = new Button("START QUIZ");
        chooseFileBtn.setFont(Font.font("Verdana", 13));
        chooseFileBtn.setLayoutX(width / 2.5);
        chooseFileBtn.setLayoutY(height / 2);

        chooseFileBtn.setOnAction(action -> {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            File file = chooser.showOpenDialog(mainScene.getWindow());

            if (file == null) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("No file choosed");
                error.showAndWait();
            } else {
                try {
                    String path = file.getAbsolutePath();
                    if (!path.contains(".txt"))
                        throw new InvalidQuizFormatException("");

                    quiz = Quiz.loadFromFile(path);
                    startQuiz();
                } catch (InvalidQuizFormatException e) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Invalid quiz format!");
                    error.setContentText("Please, choose your quiz file properly!");
                    error.showAndWait();
                }
            }
        });

        menuGroup.getChildren().addAll(welcomeText, chooseFileBtn);

        // Scene and window setup
        root.getChildren().add(menuGroup);
        mainScene = new Scene(root, width, height);
        mainScene.getStylesheets().add("style.css");
        stage.setScene(mainScene);
        stage.setTitle("Quiz");
        stage.setResizable(false);
        stage.show();
    }
}