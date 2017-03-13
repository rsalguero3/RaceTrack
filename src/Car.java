import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Car extends ImageView{

    private long timeNow;
    private int speed;
    private List<Integer> travelPath;
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
        else if (body == "Black" && wheels == "Small"){
            this.setImage(new Image("blackCar.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(180);
        }
        else if(body == "Black" && wheels == "Big") {
            this.setImage(new Image("blackSuv.png"));
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

    //I hope saw two versions
    public void Time(long time ){
        long timeNow = System.currentTimeMillis();
        //long lnSystemTime = System.currentTimeMillis();
        //System.out.println("Time: " + lnSystemTime);
        System.out.println("Time: " + timeNow);

    }
}