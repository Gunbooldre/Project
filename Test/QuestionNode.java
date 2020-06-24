import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

enum QuestionType {
    fillIn, test
}

public class QuestionNode extends Group {
    private Question question;
    private Text description = new Text();

    public QuestionType type;

    public TextField answer = new TextField();
    public ToggleGroup answerGroup = new ToggleGroup();

    public Separator isCorrect = new Separator();

    public boolean checkAnswer() {
        if (type == QuestionType.fillIn)
            return answer.getText().toLowerCase().equals(question.getAnswer().toLowerCase());
        return ((RadioButton) answerGroup.getSelectedToggle()).getText().toLowerCase()
                .equals(question.getAnswer().toLowerCase());
    }

    public QuestionNode(Question question, int index) {
        this.question = question;

        Text indexText = new Text(String.format("Problem №%d:", index));
        indexText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        VBox content = new VBox(20, indexText, description, isCorrect);
        Separator endLine = new Separator();
        endLine.setStyle("-fx-border-style: solid; -fx-border-width: -1 -1 2 -1; -fx-border-color: black;");

        Text tempText = new Text("Answer: ");
        tempText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        content.getChildren().add(tempText);

        if (question instanceof FillIn) {
            type = QuestionType.fillIn;

            String blankSpace = "";

            for (int i = 0; i < question.getAnswer().length(); blankSpace += "_", i++)
                ;

            description.setText(question.getDescription().replace("{blank}", blankSpace));

            content.getChildren().addAll(answer);
        } else {
            type = QuestionType.test;
            description.setText(question.getDescription());
            VBox options = new VBox(5);

            ArrayList<String> temp = new ArrayList<String>();

            for (int i = 0;; i++) {
                String option = ((Test) question).getOptionAt(i);
                if (option == null)
                    break;
                temp.add(option);
            }
            temp.add(question.getAnswer());
            Collections.shuffle(temp);

            temp.forEach(item -> {
                RadioButton button = new RadioButton(item);
                button.setToggleGroup(answerGroup);
                button.setFont(Font.font("Verdana", 11));
                options.getChildren().add(button);
            });

            content.getChildren().add(options);
        }

        content.getChildren().add(endLine);

        description.setFont(Font.font("Verdana", 13));
        description.setWrappingWidth(Main.width - 40);

        content.setLayoutX(20);
        content.setLayoutY(30);

        getChildren().addAll(content);
    }
}