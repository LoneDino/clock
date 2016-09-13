package clock;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import javafx.scene.shape.Line;
import static java.util.Calendar.HOUR_OF_DAY;


public class clock extends Application{
    public static void main(String[] args){
        launch(args);
    }public void start(Stage stage){
        Scanner in = new Scanner(System.in);
        VBox layout = new VBox();
        Group root = new Group();
        Scene myScene = new Scene(layout, 500, 500);
        Canvas canvas = new Canvas(500,500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        long hour = (Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        long min = (Calendar.getInstance().getTimeInMillis())/(1000*60) % 60;
        long sec = (Calendar.getInstance().getTimeInMillis())/(1000) % 60;
        long off = (hour * 60 * 60) + (min * 60) + sec;

        long nano = System.nanoTime();

        stage.setScene(new Scene (root));
        stage.show();


        new AnimationTimer (){
            public void handle (long t){
                double w = (t - nano) / 1000000000.0;
                w += off;

                double x = 200 * Math.sin(((2*Math.PI/60)*w)-Math.PI/2) + 250;
                double y = 200 * Math.cos(((2*Math.PI/60)*w)-Math.PI/2) + 250;
                double x2 = 200 * Math.sin(((2*Math.PI/3600)*w)-Math.PI/2) + 250;
                double y2 = 200 * Math.cos(((2*Math.PI/3600)*w)-Math.PI/2) + 250;
                double x3 = 50 * Math.sin(((2*Math.PI/43200)*w)-Math.PI/2) + 250;
                double y3 = 50 * Math.cos(((2*Math.PI/43200)*w)-Math.PI/2) + 250;

                Random rand = new Random();
                int color = rand.nextInt(255);
                int color2 = rand.nextInt(255);
                int color3 = rand.nextInt(255);

                gc.setFill(Color.WHITE);
                gc.fillRect(0,0,500,500);
                gc.strokeLine(250,250,y,x);
                gc.setStroke(Color.rgb(color,color2,color3));
                gc.setLineWidth(10);
                gc.strokeLine(250,250,y2,x2);
                gc.strokeLine(250,250,y3,x3);
                gc.strokeOval(0,0,500,500);


            }

        }.start();
    }
}
