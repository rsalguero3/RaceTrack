import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/* Mike was here */

/**
 * Created by rsalguero on 2/24/17.
 */
public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RaceTrack raceTrack = null;
        //TODO: Move this into the Car class and create constructors with 3 String inputs
        //and set Image depending on those strings, The 3 Strings will be wheels, engine, body
        Car car = new Car("Red","Small");

        //pop up window will let user pick raceTrack
        List<String> choices = new ArrayList<>();
        choices.add("Square");
        choices.add("Circle");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Square", choices);
        dialog.setTitle("Race Track");
        dialog.setHeaderText("Race Track");
        dialog.setContentText("Choose your Track:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            raceTrack = new RaceTrack(result.get());
        }
        else{
            primaryStage.close();
        }

        //get road path from Racetrack and place into the animation
        Path road = raceTrack.getRoad();
        PathTransition anim = new PathTransition();
        anim.setNode(car);
        anim.setPath(road);
        anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        anim.setInterpolator(Interpolator.LINEAR);

        //Car class will have randomSpeed method that will return a speed in milliseconds
        anim.setDuration(new Duration(6000));
        //complete the path only once
        anim.setCycleCount(1);

        //get the divider from RaceTrack and added to the scene
        Path divider = raceTrack.getDivider();
        Group root = new Group();
        root.getChildren().addAll(road, divider,car);
        root.setTranslateX(50);
        root.setTranslateY(50);

        GridPane mainPane = new GridPane();
        HBox buttonPanel = new HBox();
        Button start = new Button("Start");
        Button pause = new Button("Pause");
        start.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                    anim.play();
            }
        });
        pause.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Animation.Status status = anim.getStatus();
                if (status == Animation.Status.RUNNING &&
                        status != Animation.Status.PAUSED)
                    anim.pause();
            }
        });
        buttonPanel.getChildren().addAll(start, pause);
        buttonPanel.setStyle("-fx-padding: 60px");
        mainPane.add(root,0,0);
        mainPane.add(buttonPanel,3,1);
        Scene scene = new Scene(mainPane, 1000, 1000, Color.DARKGREEN);

        primaryStage.setTitle("RaceTrack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}