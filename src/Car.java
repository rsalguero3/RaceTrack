import javafx.scene.image.ImageView;

public class Car extends ImageView{

    private double totalTime;
    private int totalDistance;
    private int speed;
    private int[][] travelPath;
    public int[][] currentLocation;
    private String wheels;
    private String body;
    private String engine;

    //TODO: The constructor will have 3 String inputs, set an image depending on those strings
    //find and save the images in PNG, and save it into the same directory as the class files
    //we need 8 pictures, 4 body colors, black, red, blue, white. each will have small and big wheels
    public Car(){
        super();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int[][] getLocation(){
        return currentLocation;
    }
}