package sample;

import classes.Point;
import classes.SnakeWorld;
import constants.Direction;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {
    private long interval = 150_000_000;
    private int SIZE = 40;
    private SnakeWorld world;
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mikey's snake");
        FlowPane root = new FlowPane();
        Pane mapChart = new Pane();
        mapChart.setStyle("-fx-border-color: black");
        Label result = new Label("");
        Button restartButton = new Button("Play again");
        restartButton.setOnAction(e -> {
            root.getChildren().remove(restartButton);
            restart();
        });
        root.setHgap((int) (SIZE / 2));
        root.getChildren().addAll(result, mapChart);
        Scene scene = new Scene(root, 25*SIZE, 22*SIZE);
        world = new SnakeWorld(SIZE);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    world.changeSnakeDirection(Direction.W);
                    break;
                case RIGHT:
                    world.changeSnakeDirection(Direction.E);
                    break;
                case UP:
                    world.changeSnakeDirection(Direction.S);
                    break;
                case DOWN:
                    world.changeSnakeDirection(Direction.N);
                    break;
            }
        });
        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (!world.isGameOver()) {
                    if (now - lastUpdate >= interval) {
                        update(world, mapChart);
                        result.setText("Your score: " + world.getScore());
                        lastUpdate = now;
                    }
                    if (world.isGameOver()) {
                        result.setText(" You lost! \n Your score: " + world.getScore());
                        root.getChildren().add(restartButton);
                        this.stop();
                    }
                }
            }

        };
        primaryStage.setScene(scene);
        primaryStage.show();
        timer.start();
    }

    public void update(SnakeWorld world, Pane mapChart) {
        world.run();
        if (!world.isGameOver()) {
            mapChart.getChildren().removeIf(e -> {
                return true;
            });
            draw(world, mapChart);
        }
    }

    public void draw(SnakeWorld world, Pane mapChart) {
        int rectangleSize = (int) (SIZE / 2);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Point currentPoint = new Point(i, j);
                if (world.isOccupied(currentPoint)) {
                    if (world.isSnakeAt(currentPoint)) {
                        Rectangle rectangle = new Rectangle(rectangleSize, rectangleSize);
                        rectangle.setFill(
                                world.getSnake().getPosition().equals(currentPoint) ? Color.GREEN : Color.LIGHTGREEN);
                        rectangle.setX(rectangleSize * i);
                        rectangle.setY(rectangleSize * j);
                        mapChart.getChildren().add(rectangle);
                    } else if (world.isAppleAt(currentPoint)) {
                        Rectangle rectangle = new Rectangle(rectangleSize, rectangleSize, Color.RED);
                        rectangle.setX(rectangleSize * i);
                        rectangle.setY(rectangleSize * j);
                        mapChart.getChildren().add(rectangle);
                    } else if (world.isStoneAt(currentPoint)) {
                        Rectangle rectangle = new Rectangle(rectangleSize, rectangleSize, Color.BLACK);
                        rectangle.setX(rectangleSize * i);
                        rectangle.setY(rectangleSize * j);
                        mapChart.getChildren().add(rectangle);
                    }
                } else {
                    Rectangle rectangle = new Rectangle(rectangleSize, rectangleSize, Color.TRANSPARENT);
                    rectangle.setX(rectangleSize * i);
                    rectangle.setY(rectangleSize * j);
                    mapChart.getChildren().add(rectangle);
                }
            }
        }
    }

    public void restart(){
        world = new SnakeWorld(SIZE);
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
