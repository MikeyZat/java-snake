package classes;

import constants.Direction;
import interfaces.IObserver;
import interfaces.ISnakeWorld;

import java.util.HashMap;

public class SnakeWorld implements IObserver, ISnakeWorld {
    private Point rightCorner;
    private Point leftCorner = new Point(0, 0);
    private Snake snake;
    private Apple apple;
    private HashMap<Point, Stone> stones = new HashMap<>();
    private int score = 0;
    private boolean gameOver = false;

    public SnakeWorld(int size) {
        rightCorner = new Point(size - 1, size - 1);
        snake = new Snake(new Point(size / 2, size / 2), this);
        snake.addObserver(this);

        try {
            apple = new Apple(getRandomFreePoint());
            for (int i = 0; i < size/2; i++) {
                Point point = getRandomFreePoint();
                stones.put(point, new Stone(point));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            endGame();
        }
    }

    public void run() {
        snake.move();
    }

    public void changeSnakeDirection(Direction direction) {
        snake.changeDirection(direction);
    }

    public boolean isOccupied(Point position) {
        return isStoneAt(position) || isSnakeAt(position) || isAppleAt(position);
    }

    Point getRandomFreePoint() {
        Point newPosition = Point.getRandomPoint(leftCorner.x, rightCorner.x, leftCorner.y, rightCorner.y);
        int limit = 50;
        while (isOccupied(newPosition) && limit > 0) {
            newPosition = Point.getRandomPoint(leftCorner.x, rightCorner.x, leftCorner.y, rightCorner.y);
            limit--;
        }
        if (limit == 0) throw new RuntimeException("No free space in the map");
        return newPosition;
    }

    @Override
    public void snakeDied(Point position) {
        endGame();
    }

    @Override
    public void appleEaten(Point position) {
        score += 1;
        try {
            apple = new Apple(getRandomFreePoint());
        } catch (RuntimeException e) {
            e.printStackTrace();
            endGame();
        }
    }

    @Override
    public boolean isStoneAt(Point position) {
        return stones.get(position) != null;
    }

    @Override
    public boolean isAppleAt(Point position) {
        return apple != null && apple.getPosition().equals(position);
    }

    @Override
    public boolean isSnakeAt(Point position) {
        return snake != null && snake.getPosition().equals(position)
                || snake.getTail() != null && snake.getTail().contains(position);

    }

    @Override
    public boolean isOutsideMap(Point position) {
        return !(position.follow(leftCorner) && position.precedes(rightCorner));
    }

    public int getScore() {
        return this.score;
    }

    private void endGame() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Snake getSnake() {
        return this.snake;
    }
}
