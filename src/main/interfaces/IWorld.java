package interfaces;

import classes.Point;

public interface IWorld {
    boolean isStoneAt(Point position);
    boolean isAppleAt(Point position);
    boolean isOutsideMap(Point position);
}
