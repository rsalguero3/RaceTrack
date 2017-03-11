import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class RaceTrack {
    //Will hold 4 Car objects
    Car[] cars;

    //This is just one full square path, break it up into 4 paths and name them, A, B, C, D
    PathElement[] path ={
                    new MoveTo(0, 300),
                    new ArcTo(100, 100, 0, 100, 400, false, false),
                    new LineTo(300, 400),

                    new ArcTo(100, 100, 0, 400, 300, false, false),
                    new LineTo(400, 100),

                    new ArcTo(100, 100, 0, 300, 0, false, false),
                    new LineTo(100, 0),

                    new ArcTo(100, 100, 0, 0, 100, false, false),
                    new LineTo(0, 300),

                    new ClosePath()
            };

    //
    Path road = new Path();
    Path divider = new Path();

    //Constructor with String input to check which track to create
    public RaceTrack(String trackName){
        if(trackName == "Square"){
            //initializing the painting of the road
            road.setStroke(Color.BLACK);
            road.setStrokeWidth(75);
            road.getElements().addAll(path);
            //setting white dividers on the black path.
            divider.setStroke(Color.WHITE);
            divider.setStrokeWidth(4);
            divider.getStrokeDashArray().addAll(10.0, 10.0);
            divider.getElements().addAll(path);
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
}
