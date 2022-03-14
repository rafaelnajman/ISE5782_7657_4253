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

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // Equivalence Partitions tests ======================================================================
        // EP01 subtract two different points
        Point p1 = new Point(1, 2, 3);
        Vector p2 = new Vector(2, 3, 4);
        Point p3 = new Vector(-1, -1, -1);
        assertEquals(p3, p1.subtract(p2), "wrong subtract");

        // Boundary Value Analysis tests ======================================================================
        // BV01 subtracting point from itself
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "can't subtract point from itself");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        Point p0 = new Point(0, 0, 0);
        Point p1 = new Point(0, 0, 1);
        assertEquals(p0.distance(p1), 1, "distance of points is incorrect");
    }
}