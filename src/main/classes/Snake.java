package classes;

import constants.Direction;
import interfaces.IObserver;
import interfaces.IObserverTarget;
import interfaces.IWorld;

import java.util.ArrayList;
import java.util.List;

public class Snake implements IObserverTarget {
    private Direction direction;
    private Point position;
    private List<Point> tail = new ArrayList<>();
    private List<IObserver> observers = new ArrayList<>();
    private IWorld world;

    public Snake(Point position, IWorld world) {
        this.position = position;
        direction = Direction.N;
        this.world = world;
    }

    public void changeDirection(Direction direction) {
        if (tail.size() > 0 && this.direction.isOpposite(direction)) {
            return;
        }
        this.direction = direction;
    }

    public void move() {
        Point oldPosition = position;
        position = position.add(direction.toUnitVector());
        if (world.isStoneAt(position) || world.isOutsideMap(position) || tail.contains(position)) {
            observers.forEach(observer -> observer.snakeDied(position));
        }
        if (world.isAppleAt(position)) {
            eatApple(position);
        }
        for (int i = tail.size() - 1; i > 0; i--) {
            tail.set(i, tail.get(i - 1));
        }
        if (tail.size() > 0) tail.set(0, oldPosition);
    }

    public void eatApple(Point position) {
        tail.add(position);
        observers.forEach(observer -> observer.appleEaten(position));

    }

    public Point getPosition() {
        return this.position;
    }

    public List<Point> getTail() {
        return this.tail;
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
}
