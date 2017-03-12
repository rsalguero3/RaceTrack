import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends ImageView{

    private long timeNow;
    private int speed;
    private int[][] travelPath;
    public int[][] currentLocation;
    private String wheels;
    private String body;
    private String engine;

    //TODO: The constructor will have 3 String inputs, set an image depending on those strings
    //find and save the images in PNG, and save it into the same directory as the class files
    //we need 8 pictures, 4 body colors, black, red, blue, white. each will have small and big wheels
    //Shivanie-moved the car to the constructor...chaged to serve class not car object
    public Car(String body, String wheels ) {
        super();
        if (body == "Red" && wheels == "Small")
        {
            this.setImage(new Image("redCar.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(180);
        }
        else if(body == "Blue" && wheels == "Small")
        {
            this.setImage(new Image ("blueCar.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(180);
        }
        else if(body == "Red" && wheels == "Big"){
            this.setImage(new Image("redSuv.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(180);
        }
        else{
            this.setImage(new Image("blueSuv.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(180);
        }



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

    public void Time(long time ){
        long timeNow = System.currentTimeMillis();
        //long lnSystemTime = System.currentTimeMillis();
        //System.out.println("Time: " + lnSystemTime);
        System.out.println("Time: " + timeNow);

    }

}