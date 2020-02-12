package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        MyList<Point> listOfPoints = new MyList<>();
        //Read dataFile
        File file = new File("./src/sample/dataFile");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String pointText;
        while((pointText = br.readLine()) != null)
        {
            String[] coord = pointText.split(", ");
            Point p = new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
            listOfPoints.add(p);
        }

        //Creating a Group object
        Group root = new Group();

        for(int i = 0; i < listOfPoints.size(); i++) {
            Point p = listOfPoints.get(i);
            Rectangle rec = new Rectangle(p.getX() * 10, p.getY() * 10, 5, 5);
            root.getChildren().add(rec);
        }
        listOfPoints = Utility.grahamScan(listOfPoints);

        Polygon plg = new Polygon();

        plg.setFill(Color.rgb(200, 200, 200, 0.5));
        for(int i = 0; i < listOfPoints.size(); i++) {
            plg.getPoints().add(listOfPoints.get(i).getX() * 10);
            plg.getPoints().add(listOfPoints.get(i).getY() * 10);
        }

        root.getChildren().add(plg);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 600);

        //Setting title to the Stage
        stage.setTitle("Drawing a Rectangle");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
