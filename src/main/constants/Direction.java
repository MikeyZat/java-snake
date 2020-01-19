package constants;

import classes.Point;

public enum Direction {
    N,
    E,
    S,
    W;

    public Point toUnitVector() {
        switch (this) {
            case N: return new Point(0, 1);
            case E: return new Point(1, 0);
            case S: return new Point(0, -1);
            case W: return new Point(-1, 0);
            default: return new Point(0,0);
        }
    }

    public boolean isOpposite(Direction direction) {
        return this.toUnitVector().add(direction.toUnitVector()).equals(new Point(0,0));
    }
}
