package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    void testConstructor() {

        // Boundary Value Analysis tests ======================================================================
        // BV01  two points are equal
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 0, 2)),
                "constructed Plane with equal points");

        // BV02 points are all on the same line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 3), new Point(0, 0, 2)),
                "constructed plane must have Vectors in different directions");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // Equivalence Partitions tests ======================================================================
        // EP01 test if normal vector is correct
        Plane p1 = new Plane(new Point(0, 0, 0), new Point(0, 5, 0), new Point(5, 0, 0));
        Vector normal = new Vector(0, 0, 1);
        assertTrue(normal.equals(p1.getNormal(new Point(1, 1, 0))) ||
                normal.equals(p1.getNormal(new Point(-1, -1, 0))), "bad normal to plane");
    }

    @Test
    void testFindIntersections() {
        // Equivalence Partitions tests ======================================================================
        // EP01 test if neither orthogonal nor parallel points intersects with plane
        Plane plane = new Plane(new Point(1, 0, 0), new Vector(0, 1, 0));
        Ray ray = new Ray(new Point(0, -2, 0), new Vector(-3, 6, 0));
        assertEquals(1, plane.findIntersections(ray).size(), "ray intersects plane");
        assertEquals(new Point(-1, 0, 0), plane.findIntersections(ray).get(0), "ray intersects plane");

        // EP02 test if ray after plane and goes in opposite direction
        ray = new Ray(new Point(0, -2, 0), new Vector(3, -4, 0));
        assertEquals(null, plane.findIntersections(ray), "ray does not intersect plane");


        // Boundary Value Analysis tests ======================================================================
        // BV01  ray is parallel to plane (0 points)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 0, 1));
        assertEquals(null, plane.findIntersections(ray), "ray is parallel to plane");

        // BV02  ray is parallel to plane and is included in plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 0, 0));
        assertEquals(null, plane.findIntersections(ray), "ray is parallel to plane and is included in plane");

        // BV03  ray is orthogonal to plane and starts before plane (1 point)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 1, 0));
        assertEquals(1, plane.findIntersections(ray).size(), "ray is orthogonal to plane and starts before plane");
        assertEquals(new Point(0, 0, 0), plane.findIntersections(ray).get(0), "ray is orthogonal to plane and starts before plane");

        // BV04  ray is orthogonal to plane and starts in plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(0, 1, 0));
        assertEquals(null, plane.findIntersections(ray), "ray is orthogonal to plane and starts in plane");

        // BV05  ray is orthogonal to plane and starts after plane (0 points)
        ray = new Ray(new Point(-1, 1, 0), new Vector(0, 1, 0));
        assertEquals(null, plane.findIntersections(ray), "ray is orthogonal to plane and starts after plane");

        // BV06  ray is neither orthogonal nor parallel to plane and starts in plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 1, 0));
        assertEquals(null, plane.findIntersections(ray), "ray is neither orthogonal nor parallel to plane and starts in plane");

        // BV07  ray is neither orthogonal nor parallel to plane and starts in plane and starts at reference point (0 points)
        ray = new Ray(new Point(1, 0, 0), new Vector(1, 1, 0));
        assertEquals(null, plane.findIntersections(ray), "ray is neither orthogonal nor parallel to plane and starts in plane and starts at reference point");

    }
}