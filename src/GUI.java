import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Ricardo Salguero
public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RaceTrack raceTrack = null;
        String wheelChoice = null;
        String colorChoice = null;
        String engineChoice = null;

        //pop up window will let user pick Car Color
        List<String> bodyColor = new ArrayList<>();
        bodyColor.add("Red");
        bodyColor.add("Blue");
        bodyColor.add("Black");

        ChoiceDialog<String> carColorChoice = new ChoiceDialog<>("Red", bodyColor);
        carColorChoice.setTitle("Chose Car Color");
        carColorChoice.setHeaderText("Race Track");
        carColorChoice.setContentText("Choose your Color:");

        Optional<String> carColor = carColorChoice.showAndWait();
        if (carColor.isPresent()){
            colorChoice = carColor.get();
        }
        else{
            primaryStage.close();
        }

        //pop up window will let user pick Car engine
        List<String> engine = new ArrayList<>();
        engine.add("Turbo");
        engine.add("SuperCharger");

        ChoiceDialog<String> carEngineChoice = new ChoiceDialog<>("Turbo", engine);
        carEngineChoice.setTitle("Chose Car Engine");
        carEngineChoice.setHeaderText("Race Track");
        carEngineChoice.setContentText("Choose your Engine:");

        Optional<String> carEngine = carEngineChoice.showAndWait();
        if (carEngine.isPresent()){
            engineChoice = carEngine.get();
        }
        else{
            primaryStage.close();
        }

        //pop up window will let user pick Car
        List<String> wheel = new ArrayList<>();
        wheel.add("Small");
        wheel.add("Big");

        ChoiceDialog<String> carWheelChoice = new ChoiceDialog<>("Small", wheel);
        carWheelChoice.setTitle("Choose your wheel Size");
        carWheelChoice.setHeaderText("Race Track");
        carWheelChoice.setContentText("Choose your Wheel size:");

        Optional<String> carWheel = carWheelChoice.showAndWait();
        if (carWheel.isPresent()){
            wheelChoice = carWheel.get();
        }
        else{
            primaryStage.close();
        }

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
            raceTrack = new RaceTrack(result.get(), colorChoice, wheelChoice);
        }
        else{
            primaryStage.close();
        }
        //get road path from Racetrack and place into the animation
        Path road = raceTrack.getRoad();
        PathTransition anim = raceTrack.createAnim(0);
        anim.play();


        //get the divider from RaceTrack and added to the scene
        Path divider = raceTrack.getDivider();
        Group root = new Group();
        root.getChildren().addAll(road, divider,raceTrack.getCars()[0]);
        root.setTranslateX(50);
        root.setTranslateY(50);

        GridPane mainPane = new GridPane();
        HBox buttonPanel = new HBox();
        Button start = new Button("Start");
        Button pause = new Button("Pause");
        RaceTrack finalRaceTrack = raceTrack;
        /*start.setOnAction(event -> {
                anim.play();
        });
        pause.setOnAction(event -> {
            Animation.Status status = anim.getStatus();
            if (status == Animation.Status.RUNNING &&
                    status != Animation.Status.PAUSED)
                anim.pause();
        });
        */
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