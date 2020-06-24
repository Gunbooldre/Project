import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.SubScene;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//The author is
// Dias Muratbayev
//Everything wrote only by me :D


public class Game extends Application  {
    private Map map ;
     private Food food ;
    private Player player ;

        //Group of all elements in start window
        private static Group StartWindow = new Group();

        // Window width
        public static final float width = 150f;

        // Window height
        public static final float height = 150f;

        private static BorderPane pane;

        public void start(Stage primatyStage) throws Exception {
        Button btn = new Button("----START----");
        btn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
                showGame();
            }
        });
        // Game objects
        map = new Map("map1.txt");
        player = new MyPlayer(map);
       // player = new MyBotPlayer(map);

        //pane
        pane = new BorderPane();
        StartWindow.getChildren().add(btn);
        pane.setCenter(StartWindow);

        //scene
        Scene scene = new Scene(pane,width,height);
        primatyStage.setScene(scene);
        primatyStage.setResizable(true);
        primatyStage.show();
    }
            //showGame Method
            public void showGame(){
                Stage stage = new Stage();
                stage.initOwner(pane.getScene().getWindow());

                VBox box = new VBox();
                box.setPadding(new Insets(10));
                box.setAlignment(Pos.CENTER);

                Label label = new Label("Please enter your name");
                TextField textUser = new TextField();

                Button btnLogin = new Button();
                btnLogin.setText("Enter");

                btnLogin.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {

                food = new Food(map,player);

                Group root = new Group(map);
                stage.setScene(new Scene(root, map.getSize() * map.getUnit(), map.getSize() * map.getUnit()));
                root.getScene().addEventHandler(KeyEvent.KEY_RELEASED , key -> {
                    player.move(key.getCode());
                });
            }
        });


        box.getChildren().add(label);
        box.getChildren().add(textUser);
        box.getChildren().add(btnLogin);
        Scene scene = new Scene(box, 200, 250);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
                
            
        }

        public static void main(String[] args) {
            launch(args);
    }
}


