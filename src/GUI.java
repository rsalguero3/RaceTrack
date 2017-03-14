import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
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
        choices.add("Track 1");
        choices.add("Track 2");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Track 1", choices);
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
        TextArea timerPanel = new TextArea();
        //get road path from Racetrack and place into the animation
        Path road = raceTrack.getRoad();
        PathTransition[] anim = raceTrack.createAnim(0);
        PathTransition[] anim1 = raceTrack.createAnim(1);
        PathTransition[] anim2 = raceTrack.createAnim(2);
        PathTransition[] anim3 = raceTrack.createAnim(3);
        //play the animation from stop 1 through stop 4 for each car
        SequentialTransition st = new SequentialTransition(anim[0]);
        SequentialTransition st1 = new SequentialTransition(anim1[0]);
        SequentialTransition st2 = new SequentialTransition(anim2[0]);
        SequentialTransition st3 = new SequentialTransition(anim3[0]);
        RaceTrack finalRaceTrack1 = raceTrack;
        long startTime = 0;
        long finalStartTime = startTime;
        //get the car time stamp and when animation finishes it will subtract the current time with starting
        st.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("UserCar Time: " + Long.toString((finalRaceTrack1.getCars()[0].TimeEnd() -
                        finalRaceTrack1.getCars()[0].getTimeNow()) / 1000)
                + "\n");
            }
        });
        st1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Car 1 Time: " + Long.toString((finalRaceTrack1.getCars()[1].TimeEnd() -
                        finalRaceTrack1.getCars()[1].getTimeNow()) / 1000) + "\n");
            }
        });
        st2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Car 2 Time: " + Long.toString((finalRaceTrack1.getCars()[2].TimeEnd() -
                        finalRaceTrack1.getCars()[2].getTimeNow()) / 1000) + "\n");
            }
        });
        st3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Car 3 Time: " + Long.toString((finalRaceTrack1.getCars()[3].TimeEnd() -
                        finalRaceTrack1.getCars()[3].getTimeNow()) / 1000) + "\n");
            }
        });
        st.play();
        st1.play();
        st2.play();
        st3.play();
        //Start the car timer
        raceTrack.getCars()[0].TimeNow();
        raceTrack.getCars()[1].TimeNow();
        raceTrack.getCars()[2].TimeNow();
        raceTrack.getCars()[3].TimeNow();


        //get the divider from RaceTrack and added to the scene
        Path divider = raceTrack.getDivider();
        Group root = new Group();
        //place all the track pieces into root
        root.getChildren().addAll(road, divider,raceTrack.getCars()[0], raceTrack.getCars()[1],
                raceTrack.getCars()[2], raceTrack.getCars()[3]);
        root.setTranslateX(50);
        root.setTranslateY(50);
        root.setStyle("-fx-background-color: coral");

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
        mainPane.add(timerPanel,4,1);
        Scene scene = new Scene(mainPane, 1500, 1000);

        primaryStage.setTitle("RaceTrack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}