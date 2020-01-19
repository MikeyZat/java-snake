package constants;

import classes.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void toUnitVector() {
        assertEquals(new Point(1, 0), Direction.E.toUnitVector());
        assertEquals(new Point(0, -1), Direction.S.toUnitVector());
        assertEquals(new Point(0, 1), Direction.N.toUnitVector());
        assertEquals(new Point(-1, 0), Direction.W.toUnitVector());
    }
}