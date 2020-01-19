package interfaces;

import classes.Point;

public interface ISnakeWorld extends IWorld {
    boolean isSnakeAt(Point position);
}
