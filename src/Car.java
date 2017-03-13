import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.PathElement;

import java.util.*;

import java.util.List;

public class Car extends ImageView{
    private long timeNow;
    private long timeEnd;
    private int speed;
    private PathElement[][] travelPath;
    private String wheels;
    private String body;
    private String engine;

    public Car(String body, String wheels ) {
        super();
        if (body == "Red" && wheels == "Small")
        {
            this.setImage(new Image("redCar.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        else if(body == "Blue" && wheels == "Small")
        {
            this.setImage(new Image ("blueCar.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        else if(body == "Red" && wheels == "Big"){
            this.setImage(new Image("redSuv.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        else if (body == "Black" && wheels == "Small"){
            this.setImage(new Image("blackCar.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        else if(body == "Black" && wheels == "Big") {
            this.setImage(new Image("blackSuv.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        else{
            this.setImage(new Image("blueSuv.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        this.setSpeed();
    }
    public void setSpeed() {
        Random randomNum = new Random();
        int ranNum;
        ranNum = randomNum.nextInt(10) + 5;
        this.speed += ranNum * 500;
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

    //I hope saw two versions
    public long TimeNow(){
        timeNow = System.currentTimeMillis();
        return timeNow;
    }

    public long TimeEnd(){
        timeEnd = System.currentTimeMillis();
        return timeEnd;
    }
    public long getTimeNow() {
        return timeNow;
    }

    public void setTravelPath(PathElement[][] travelPath) {
        this.travelPath = travelPath;
    }

    public PathElement[][] getTravelPath() {
        return travelPath;
    }

    public int getSpeed() {
        return speed;
    }

}