package classes;

import interfaces.IWorldObject;

public class Apple implements IWorldObject {
    private Point position;

    public Apple(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }
}
