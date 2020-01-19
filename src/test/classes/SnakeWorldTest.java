package classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeWorldTest {
    SnakeWorld world = new SnakeWorld(40);
    @Test
    public void snakeDied() {
    }

    @Test
    public void isSnakeAt() {
        assertFalse(world.isSnakeAt(new Point(10,10)));
        assertTrue(world.isSnakeAt(new Point(20,20)));
    }

    @Test
    public void isOutsideMap() {
        Point p_0_0 = new Point(0,0);
        Point p_39_39 = new Point(39,39);
        Point p_10_10 = new Point(10,10);
        Point p_0_39 = new Point(0,39);
        Point p_39_0 = new Point(39,0);
        Point p_45_0 = new Point(45,0);
        Point p_0_45 = new Point(0,45);
        Point p_5_5 = new Point(-5,-5);
        Point p_45_45 = new Point(45,45);
        Point p_5_0 = new Point(-5,0);
        Point p_0_5 = new Point(0,-5);
        assertFalse(world.isOutsideMap(p_0_0));
        assertFalse(world.isOutsideMap(p_39_39));
        assertFalse(world.isOutsideMap(p_10_10));
        assertFalse(world.isOutsideMap(p_0_39));
        assertFalse(world.isOutsideMap(p_39_0));
        assertTrue(world.isOutsideMap(p_45_0));
        assertTrue(world.isOutsideMap(p_0_45));
        assertTrue(world.isOutsideMap(p_5_5));
        assertTrue(world.isOutsideMap(p_45_45));
        assertTrue(world.isOutsideMap(p_5_0));
        assertTrue(world.isOutsideMap(p_0_5));
    }
}