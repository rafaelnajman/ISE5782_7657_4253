package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Sphere class
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // Equivalence Partitions tests ======================================================================
        // EP01 get normal of point on sphere
        Sphere s1 = new Sphere (new Point (0,0,0), 1);
        Vector normal = new Vector(1,0,0);
        assertEquals(normal, s1.getNormal(new Point(1,0,0)), "Bad normal for sphere");
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point intersectionPoint = new Point(0,0,0);
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-1.5, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(intersectionPoint, result.get(0),"Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 1, 0))),
                        "Ray starts after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point p3 = new Point(1,1,0);
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p3, result.get(0), "Ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, -1, 0)));
        assertEquals(null, result, "Wrong number of points");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(0,0,0);
        p2 = new Point(2,0,0);
        result = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray starts before sphere and goes inside through the center");

        // TC14: Ray starts at sphere and goes inside (1 points)
        Point p = new Point(0,0,0);
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p, result.get(0), "Ray starts at sphere and goes inside, passing through center");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p, result.get(0), "Ray starts inside sphere, passing through center");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p, result.get(0), "Ray starts at center of the sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(3, 0, 0)));
        assertEquals(null, result, "Wrong number of points");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(4, 0, 0)));
        assertEquals(null, result, "Wrong number of points");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, -2, 0)));
        assertEquals(null, result, "Wrong number of points");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, -2, 0)));
        assertEquals(null, result, "Wrong number of points");

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 2, 0)));
        assertEquals(null, result, "Wrong number of points");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 0, 1)));
        assertEquals(null, result, "Wrong number of points");

    }

}