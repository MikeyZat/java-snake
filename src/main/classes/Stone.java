package classes;

import interfaces.IWorldObject;

public class Stone implements IWorldObject {
    Point position;

    public Stone(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }
}
