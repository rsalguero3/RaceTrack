import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.Random;

//Ricardo Salguero
public class RaceTrack {
    //Will hold 4 Car objects
    String trackName;
    Car[] cars = new Car[4];

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

    //second Track
    PathElement[] secondpathAtoB = {
            new MoveTo(0, 300),
            new QuadCurveTo(0,50,100,200),
            new LineTo(300, 400),
    };
    PathElement[] secondpathBtoC = {
            new MoveTo(300, 400),
            new ArcTo(100, 100, 0, 400, 300, false, false),
            new LineTo(400, 100),
    };
    PathElement[] secondpathCtoD = {
            new MoveTo(400, 100),
            new CubicCurveTo(100,30,20,20,40,40),
            new LineTo(100, 0),
    };
    PathElement[] secondpathDtoA = {
            new MoveTo(100, 0),
            new QuadCurveTo(100,100,100,100),
            new LineTo(0, 300),
    };

    Path road;
    Path divider;

    //Constructor with String input to check which track to create
    public RaceTrack(String trackName, String carColor, String carWheel) {
        road = new Path();
        divider = new Path();
        if (trackName == "Track 1") {
            this.trackName = trackName;
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

            Car robotCar1 = new Car(carColor, carWheel);
            randomStops(robotCar1);
            cars[1] = robotCar1;

            Car robotCar2 = new Car(carColor, carWheel);
            randomStops(robotCar2);
            cars[2] = robotCar2;

            Car robotCar3 = new Car(carColor, carWheel);
            randomStops(robotCar3);
            cars[3] = robotCar3;

        }
        else {
            this.trackName = trackName;
            //initializing the painting of the road
            road.setStroke(Color.BLACK);
            road.setStrokeWidth(75);
            road.getElements().addAll(secondpathAtoB);
            road.getElements().addAll(secondpathBtoC);
            road.getElements().addAll(secondpathCtoD);
            road.getElements().addAll(secondpathDtoA);
            //setting white dividers on the black path.
            divider.setStroke(Color.WHITE);
            divider.setStrokeWidth(4);
            divider.getStrokeDashArray().addAll(10.0, 10.0);
            divider.getElements().addAll(secondpathAtoB);
            divider.getElements().addAll(secondpathBtoC);
            divider.getElements().addAll(secondpathCtoD);
            divider.getElements().addAll(secondpathDtoA);

            Car userCar = new Car(carColor, carWheel);
            randomStops(userCar);
            cars[0] = userCar;

            Car robotCar1 = new Car(carColor, carWheel);
            randomStops(robotCar1);
            cars[1] = robotCar1;

            Car robotCar2 = new Car(carColor, carWheel);
            randomStops(robotCar2);
            cars[2] = robotCar2;

            Car robotCar3 = new Car(carColor, carWheel);
            randomStops(robotCar3);
            cars[3] = robotCar3;
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
        int random = new Random().nextInt(4);
        //auto assign the next stops based on what is the starting position
        if (trackName == "Track 1") {
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
        if(trackName == "Track 2"){
            if (random == 0) {
                path[0] = secondpathAtoB;
                path[1] = secondpathBtoC;
                path[2] = secondpathCtoD;
                path[3] = secondpathDtoA;
            }
            if (random == 1) {
                path[0] = secondpathBtoC;
                path[1] = secondpathCtoD;
                path[2] = secondpathDtoA;
                path[3] = secondpathAtoB;
            }
            if (random == 2) {
                path[0] = secondpathCtoD;
                path[1] = secondpathDtoA;
                path[2] = secondpathAtoB;
                path[3] = secondpathBtoC;
            }
            if (random == 3) {
                path[0] = secondpathDtoA;
                path[1] = secondpathAtoB;
                path[2] = secondpathBtoC;
                path[3] = secondpathCtoD;
            }
            car.setTravelPath(path);
        }
    }

    public PathTransition[] createAnim(int car) {
        PathTransition[] animArray = new PathTransition[4];
        //create 4 animations
        for(int i = 0; i < 4; i++) {
            PathTransition anim = new PathTransition();
            anim.setNode(this.getCars()[car]);
            Path carPath = new Path();
            //for loop to get all the path elements and create one animation from one stop to another
            for(int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    carPath.getElements().addAll(this.getCars()[car].getTravelPath()[r][c]);
                }
                anim.setPath(carPath);
                anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                anim.setInterpolator(Interpolator.LINEAR);

                //Car class will have randomSpeed method that will return a speed in milliseconds
                anim.setDuration(new Duration(this.getCars()[car].getSpeed()));
                //complete the path only once
                anim.setCycleCount(1);
                animArray[r] = anim;
            }

        }
        return animArray;
    }
}
