package sample;

import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    //These are the variables that are used throughout the program.
    //This part contains only the declaration process.
    private Stage stage;
    private Scene scene;
    private int i;
    private ArrayList<Points> JM;
    private Pane pane;
    private Button JMarch;
    private Points source;
    private Points destin;
    private Button GScan;
    private Button QHull;
    private Button map1;
    private Label selectMap;
    private Button map2;
    private Label heading;
    private Label landmark;
    private Label obstacle;
    private Button set;
    private TextField sourcex, destinx, sourcey, destiny;
    int count;
    private Button back;
    private Label prompt;
    private int count1 = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //This stage has the initialization of all the variables declared above.
        stage = new Stage();
        primaryStage = stage;
        prompt = new Label("Select Source by left clicking.");
        prompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        prompt.setTextFill(Color.YELLOW);
        //Configuring the set Button to set the Source and Destination coordinates.
        set = new Button("SET");
        set.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        set.setLayoutX(400);
        set.setLayoutY(570);

        //This is the exit button.
        back = new Button("EXIT");

        //These are the buttons that are used to give the points of source and destination by user input.
        sourcex = new TextField("Source x (max 840)");
        sourcex.setStyle("-fx-background-color: YELLOW");
        destinx = new TextField("Destination x (max 840)");
        destinx.setStyle("-fx-background-color: BLUE");
        sourcex.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        destinx.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        sourcey = new TextField("Source y (max 550)");
        sourcey.setStyle("-fx-background-color: YELLOW");
        destiny = new TextField("Destination y (max 550)");
        destiny.setStyle("-fx-background-color: BLUE");
        sourcey.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        destiny.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        back.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        back.setTextFill(Color.RED);
        pane = new Pane();

        //These are the lables to assist the user on the main screen.
        landmark = new Label("Select the points(landmarks) on the map by left clicking.");
        obstacle = new Label("Select the obstacle by right clicking on the map.");

        //Select Map Label.
        selectMap = new Label("Select Map");
        selectMap.setTextFill(Color.YELLOW);
        pane.getChildren().add(selectMap);

        //The Convex Hull Algorithum Buttons.
        JMarch = new Button("Jarvis March");
        GScan = new Button("Graham Scan");
        QHull = new Button("Quick Hull");
        JMarch.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        GScan.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        QHull.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        JMarch.setTextFill(Color.RED);
        GScan.setTextFill(Color.RED);
        QHull.setTextFill(Color.RED);
        JMarch.setLayoutX(150);
        GScan.setLayoutX(370);
        QHull.setLayoutX(600);
        scene = new Scene(pane, 850, 650);
        //To configure the layout of the user input.
        sourcex.setLayoutX(0);
        sourcex.setLayoutY(560);
        destinx.setLayoutX(0);
        destinx.setLayoutY(600);
        sourcey.setLayoutX(550);
        sourcey.setLayoutY(560);
        destiny.setLayoutX(550);
        destiny.setLayoutY(600);
        set.setTextFill(Color.RED);
        stage.setScene(scene);

        //HEADING ON MAIN PAGE.
        heading = new Label("AV007");

        //Arraylist to store the obstacles.
        ArrayList<Points> points = new ArrayList<Points>();

        //ArrayList to store the landmarks and the source and destination.
        ArrayList<Points> abs = new ArrayList<Points>();

        //Styling the pane.
        pane.setStyle("-fx-background-color: BLACK");


        //ArrayList to store the output points of the JarvisMarch Algorithum.
        JM = new ArrayList<Points>();

        //Styling the labels.
        landmark.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        obstacle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        landmark.setLayoutX(130);
        landmark.setLayoutY(250);
        obstacle.setLayoutX(180);
        obstacle.setLayoutY(350);
        pane.getChildren().add(landmark);
        pane.getChildren().add(obstacle);
        landmark.setTextFill(Color.YELLOW);
        obstacle.setTextFill(Color.YELLOW);

        //Configuring the map buttons.
        map1 = new Button("MAP 1");
        map2 = new Button("MAP 2");
        map1.setLayoutX(330);
        map1.setLayoutY(200);
        map2.setLayoutX(430);
        map2.setLayoutY(200);
        map1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        map2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        selectMap.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        selectMap.setLayoutX(360);
        selectMap.setLayoutY(130);

        //Configuring the heading.
        heading.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        heading.setLayoutX(340);
        heading.setLayoutY(30);
        heading.setTextFill(Color.YELLOW);
        pane.getChildren().add(map1);
        pane.getChildren().add(map2);
        pane.getChildren().add(heading);


        //Loading the images background of map1 and map2.
        Image m1 = new Image("map/map1.jpg");
        Image m2 = new Image("map/map2.jpg");
        ImageView i1 = new ImageView(m1);
        ImageView i2 = new ImageView(m2);
        //This part defines the points.
        //By clicking the left button you can select the LandMark and by right clicking you can select the obstacles.
        prompt.setLayoutX(0);
        prompt.setLayoutY(600);
        prompt.setTextFill(Color.BLUE);
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (count1 == 0){
                    Circle circle1 = new Circle(10);
                    circle1.setFill(Color.YELLOW);
                    pane.getChildren().add(circle1);
                    circle1.setLayoutX(event.getX());
                    circle1.setLayoutY(event.getY());
                    Points p = new Points(event.getX(), event.getY());
                    abs.add(p);
                    prompt.setText("Select Destination by left clicking.");
                    prompt.setTextFill(Color.BLUE);
                }
                if (count1 == 1){
                    Circle circle1 = new Circle(10);
                    circle1.setFill(Color.BLUE);
                    pane.getChildren().add(circle1);
                    circle1.setLayoutX(event.getX());
                    circle1.setLayoutY(event.getY());
                    Points p = new Points(event.getX(), event.getY());
                    abs.add(p);
                    prompt.setText("Now Select the landmarks and obstacles.");
                    prompt.setTextFill(Color.BLUE);
                }
                if (count1 >= 2) {
                    ;
                    if (event.getButton() == MouseButton.PRIMARY) {
                        Circle circle1 = new Circle(10);
                        circle1.setFill(Color.RED);
                        pane.getChildren().add(circle1);
                        circle1.setLayoutX(event.getX());
                        circle1.setLayoutY(event.getY());
                        Points p = new Points(event.getX(), event.getY());
                        abs.add(p);
                    } else {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            Circle circle = new Circle(10);
                            circle.setFill(Color.BLACK);
                            pane.getChildren().add(circle);
                            circle.setLayoutX(event.getX());
                            circle.setLayoutY(event.getY());
                            Points p = new Points(event.getX(), event.getY());
                            points.add(p);
                        }
                    }
                }
                count1++;
            }
        });

        //Confuging the clicking operation of the map1 button.
        map1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().removeAll(selectMap, map1, map2, landmark, obstacle);
                pane.getChildren().add(i1);
                pane.getChildren().add(back);
                pane.getChildren().addAll(JMarch, GScan, QHull, prompt);
            }
        });

        //Configuriong the clicking operation on map2 button.
        map2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().removeAll(selectMap, map1, map2, landmark, obstacle);
                pane.getChildren().add(i2);
                pane.getChildren().add(back);
                pane.getChildren().addAll(JMarch, QHull, GScan, prompt);
            }
        });

        //This button handles the process when you click the exit button.
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (pane.getChildren().contains(i1)){
                    pane.getChildren().removeAll(i1);
                }
                if (pane.getChildren().contains(i2)){
                    pane.getChildren().removeAll(i2);
                }
                pane.getChildren().removeAll(back, JMarch, GScan, QHull, sourcex, sourcey, destinx, destiny, set);
                pane.getChildren().addAll(selectMap, map1, map2, landmark, obstacle);
                pane.setStyle("-fx-background-color: BLACK");
                stage.close();
            }
        });

        //Invokes the Jarvish March Algorithum on the selected points.
        JMarch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                JarvisMarch jarvismarch = new JarvisMarch();
                JM = jarvismarch.points_in_hull(points, points.size());
                for (i = 0;i < JM.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.BLACK);
                    line.setStartX(JM.get(i).x);
                    line.setStartY(JM.get(i).y);
                    line.setEndX(JM.get((i + 1) % JM.size()).x);
                    line.setEndY(JM.get((i + 1) % JM.size()).y);
                    pane.getChildren().add(line);
                }
                JarvisMarch jarvismarch1 = new JarvisMarch();
                ArrayList<Points> JM = jarvismarch.points_in_hull(abs, abs.size());
                for (i = 0;i < JM.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.RED);
                    line.setStartX(JM.get(i).x);
                    line.setStartY(JM.get(i).y);
                    line.setEndX(JM.get((i + 1) % JM.size()).x);
                    line.setEndY(JM.get((i + 1) % JM.size()).y);
                    pane.getChildren().add(line);
                }
                ArrayList<Points> JM1 = jarvismarch.points_in_hull(points, points.size());
                for (i = 0;i < JM1.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.BLACK);
                    line.setStartX(JM1.get(i).x);
                    line.setStartY(JM1.get(i).y);
                    line.setEndX(JM1.get((i + 1) % JM1.size()).x);
                    line.setEndY(JM1.get((i + 1) % JM1.size()).y);
                    pane.getChildren().add(line);
                }
            }
        });

        //This button is used to set the starting and teh destination points by the user.
        set.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                source = new Points(Double.parseDouble(sourcex.getText()), Double.parseDouble(sourcey.getText()));
                destin = new Points(Double.parseDouble(destinx.getText()), Double.parseDouble(destiny.getText()));
                abs.add(source);
                abs.add(destin);
                Circle circle = new Circle(10);
                circle.setFill(Color.YELLOW);
                circle.setLayoutX(source.x);
                circle.setLayoutY(source.y);
                pane.getChildren().add(circle);
                Circle circle1 = new Circle(10);
                circle1.setFill(Color.PURPLE);
                circle1.setLayoutX(destin.x);
                circle1.setLayoutY(destin.y);
                pane.getChildren().add(circle1);
            }
        });

        //Invokes teh Graham Scan Algorithum.
        GScan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GrahamScan grahamscan = new GrahamScan();
                ArrayList<Points> GS = new ArrayList<Points>();
                GS = grahamscan.points_in_hull(points, points.size());
                for (i = 0;i < GS.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.BLACK);
                    line.setStartX(GS.get(i).x);
                    line.setStartY(GS.get(i).y);
                    line.setEndX(GS.get((i + 1) % GS.size()).x);
                    line.setEndY(GS.get((i + 1) % GS.size()).y);
                    pane.getChildren().add(line);
                }
                GrahamScan grahamscan1 = new GrahamScan();
                ArrayList<Points> GS1 = new ArrayList<Points>();
                GS1 = grahamscan.points_in_hull(abs, abs.size());
                for (i = 0;i < GS1.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.RED);
                    line.setStartX(GS1.get(i).x);
                    line.setStartY(GS1.get(i).y);
                    line.setEndX(GS1.get((i + 1) % GS1.size()).x);
                    line.setEndY(GS1.get((i + 1) % GS1.size()).y);
                    pane.getChildren().add(line);
                }
            }
        });

        //Invokes the Chans algorithum also knows as the quickhull.
        QHull.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GrahamScan grahamscan = new GrahamScan();
                ArrayList<Points> GS = new ArrayList<Points>();
                GS = grahamscan.points_in_hull(points, points.size());
                for (i = 0;i < GS.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.BLACK);
                    line.setStartX(GS.get(i).x);
                    line.setStartY(GS.get(i).y);
                    line.setEndX(GS.get((i + 1) % GS.size()).x);
                    line.setEndY(GS.get((i + 1) % GS.size()).y);
                    pane.getChildren().add(line);
                }
                GrahamScan grahamscan1 = new GrahamScan();
                ArrayList<Points> GS1 = new ArrayList<Points>();
                GS1 = grahamscan1.points_in_hull(abs, abs.size());
                for (i = 0;i < GS1.size();i++) {
                    Line line = new Line();
                    line.setStrokeWidth(5);
                    line.setStroke(Color.RED);
                    line.setStartX(GS1.get(i).x);
                    line.setStartY(GS1.get(i).y);
                    line.setEndX(GS1.get((i + 1) % GS1.size()).x);
                    line.setEndY(GS1.get((i + 1) % GS1.size()).y);
                    pane.getChildren().add(line);
                }
            }
        });
        stage.show();
    }
    //Main Function.
    public static void main(String[] args) {
        launch(args);
    }
}
