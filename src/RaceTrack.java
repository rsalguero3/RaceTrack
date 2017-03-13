import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.Random;

//Ricardo Salguero
public class RaceTrack {
    //Will hold 4 Car objects
    int numCars = 4;

    Car[] cars = new Car[numCars];

    PathElement[] pathAtoB = {
            new MoveTo(0, 300),
            new ArcTo(100, 100, 0, 100, 400, false, false),
            new LineTo(300, 400),
    };
    PathElement[] pathBtoC = {
            new MoveTo(300, 400),
            new ArcTo(100, 100, 0, 400, 300, false, false),
            new LineTo(400, 100),
    };
    PathElement[] pathCtoD = {
            new MoveTo(400, 100),
            new ArcTo(100, 100, 0, 300, 0, false, false),
            new LineTo(100, 0),
    };
    PathElement[] pathDtoA = {
            new MoveTo(100, 0),
            new ArcTo(100, 100, 0, 0, 100, false, false),
            new LineTo(0, 300),
    };

    Path road = new Path();
    Path divider = new Path();

    //Constructor with String input to check which track to create
    public RaceTrack(String trackName, String carColor, String carWheel) {
        if (trackName == "Square") {
            //initializing the painting of the road
            road.setStroke(Color.BLACK);
            road.setStrokeWidth(75);
            road.getElements().addAll(pathAtoB);
            road.getElements().addAll(pathBtoC);
            road.getElements().addAll(pathCtoD);
            road.getElements().addAll(pathDtoA);
            //setting white dividers on the black path.
            divider.setStroke(Color.WHITE);
            divider.setStrokeWidth(4);
            divider.getStrokeDashArray().addAll(10.0, 10.0);
            divider.getElements().addAll(pathAtoB);
            divider.getElements().addAll(pathBtoC);
            divider.getElements().addAll(pathCtoD);
            divider.getElements().addAll(pathDtoA);

            Car userCar = new Car(carColor, carWheel);
            randomStops(userCar);
            cars[0] = userCar;
        } else {

            //TODO: create a circle track
        }


    }

    public Path getRoad() {
        return road;
    }

    public Path getDivider() {
        return divider;
    }

    public Car[] getCars() {
        return cars;
    }

    //creates a random path from 0-4, 0 = AtoB , 1 = BtoC, 2 = CtoD, 3 = DtoA
    public void randomStops(Car car) {
        PathElement[][] path = new PathElement[4][3];
        //int random = new Random().nextInt(5);
        int random = 0;
        //auto assign the next stops based on what is the starting position
        if (random == 0) {
            path[0] = pathAtoB;
            path[1] = pathBtoC;
            path[2] = pathCtoD;
            path[3] = pathDtoA;
        }
        if (random == 1) {
            path[0] = pathBtoC;
            path[1] = pathCtoD;
            path[2] = pathDtoA;
            path[3] = pathAtoB;
        }
        if (random == 2) {
            path[0] = pathCtoD;
            path[1] = pathDtoA;
            path[2] = pathAtoB;
            path[3] = pathBtoC;
        }
        if (random == 3) {
            path[0] = pathDtoA;
            path[1] = pathAtoB;
            path[2] = pathBtoC;
            path[3] = pathCtoD;
        }
        car.setTravelPath(path);
    }

    public PathTransition createAnim(int car) {
        PathTransition anim = new PathTransition();
        anim.setNode(this.getCars()[car]);
        Path carPath = new Path();
        //for loop to get all the path elements and create one animation from one stop to another
        for (int c = 0; c < 3; c++) {
            carPath.getElements().addAll(this.getCars()[car].getTravelPath()[this.getCars()[car].getCurrentStop()][c]);
        }
        anim.setPath(carPath);
        anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        anim.setInterpolator(Interpolator.LINEAR);

        //Car class will have randomSpeed method that will return a speed in milliseconds
        anim.setDuration(new Duration(this.getCars()[car].getSpeed()));
        //complete the path only once
        anim.setCycleCount(1);
        this.getCars()[car].setCurrentStop(this.getCars()[car].getCurrentStop() + 1);
        return anim;
    }
}
