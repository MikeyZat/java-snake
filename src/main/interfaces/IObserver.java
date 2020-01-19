package interfaces;

import classes.Point;

public interface IObserver {

    void snakeDied(Point position);
    void appleEaten(Point position);
}
