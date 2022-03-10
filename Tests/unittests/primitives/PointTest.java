package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        Vector p2 = new Vector(2, 3, 4);
        Point p3 = new Point(3, 5, 7);
        assertEquals(p3, p1.add(p2));
    }

    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Vector p2 = new Vector(2, 3, 4);
        Point p3 = new Point(-1, -1, -1);
        assertEquals(p3, p1.subtract(p2));
    }



}