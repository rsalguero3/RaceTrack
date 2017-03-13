import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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
        new MoveTo(400,100),
        new ArcTo(100, 100, 0, 300, 0, false, false),
        new LineTo(100, 0),
    };
    PathElement[] pathDtoA = {
        new MoveTo(100,0),
        new ArcTo(100, 100, 0, 0, 100, false, false),
        new LineTo(0, 300),
    };

    Path road = new Path();
    Path divider = new Path();

    //Constructor with String input to check which track to create
    public RaceTrack(String trackName, String carColor, String carWheel){
        if(trackName == "Square"){
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
        }
        else{

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

    //creates a random list from 0-4, 0 = AtoB , 1 = BtoC, 2 = CtoD, 3 = DtoA
    public void randomStops(Car car){
        PathElement[][] path = new PathElement[4][3];
        int random = new Random().nextInt(4);
        //auto assign the next stops based on what is the starting position
        if(random == 0){
            path[0] = pathAtoB;
            path[1] = pathBtoC;
            path[2] = pathCtoD;
            path[3] = pathDtoA;
        }
        else if(random == 1){
            path[0] = pathBtoC;
            path[1] = pathCtoD;
            path[2] = pathDtoA;
            path[3] = pathAtoB;
        }
        else if(random == 2){
            path[0] = pathCtoD;
            path[1] = pathDtoA;
            path[2] = pathAtoB;
            path[3] = pathBtoC;
        }
        else{
            path[0] = pathDtoA;
            path[0] = pathAtoB;
            path[0] = pathBtoC;
            path[0] = pathCtoD;
        }
        car.setTravelPath(path);
    }
}
