package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Point class
 */
class PointTest {
    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // Equivalence Partitions tests ======================================================================
        // EP01 add two different positive points
        Point p1 = new Point(1, 2, 3);
        Vector p2 = new Vector(2, 3, 4);
        Point p3 = new Point(3, 5, 7);
        assertEquals(p3, p1.add(p2));

        // EP02 add two different points (one positive and one negative)
        p1 = new Point(1, 2, 3);
        p2 = new Vector(-2, -3, -4);
        p3 = new Point(-1, -1, -1);
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
        assertEquals(p3, p1.subtract(p2), "wrong subtract for different points");

        // EP02 subtract from 0 point
        p1 = new Point(0,0,0);
        p2 = new Vector(2, 3, 4);
        p3 = new Vector(-2,-3,-4);
        assertEquals(p3, p1.subtract(p2), "wrong subtract from 0 point");

        // Boundary Value Analysis tests ======================================================================
        // BV01 subtracting point from itself
        Point p4 = new Point(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> p4.subtract(p4), "can't subtract point from itself");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // Equivalence Partitions tests ======================================================================
        // EP01 distance between two different points
        Point p0 = new Point(0, 0, 0);
        Point p1 = new Point(0, 0, 1);
        assertEquals(p0.distance(p1), 1, "distance of points is incorrect");

        // Boundary Value Analysis tests ======================================================================
        // BV01 distance from point to itself
        assertEquals(p0.distance(p0), 0, "distance of point to itself is not 0");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared(){
        // Equivalence Partitions tests ======================================================================
        // EP01 distance squared between two different points
        Point p0 = new Point(0, 0, 1);
        Point p1 = new Point(0, 0, 3);
        assertEquals(p0.distanceSquared(p1), 4, "distance of points is incorrect");

        // Boundary Value Analysis tests ======================================================================
        // BV01 distance squared from point to itself
        assertEquals(p0.distanceSquared(p0), 0, "distance of point to itself is not 0");
    }
}