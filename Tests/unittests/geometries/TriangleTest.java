package unittests.geometries;

import geometries.Plane;
import geometries.Polygon;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Triangle class
 */
class TriangleTest {

    @Test
    void getNormal() {
        // Equivalence Partitions tests ======================================================================
        // EP01 test if normal vector is correct
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(0, 5, 0), new Point(5, 0, 0));
        Vector normal = new Vector(0, 0, 1);
        assertTrue(normal.equals(triangle.getNormal(new Point(1, 1, 0))) ||
                normal.equals(triangle.getNormal(new Point(-1, -1, 0))), "bad normal to triangle");
    }

    @Test
    void findIntersections(){
        // Equivalence Partitions tests ======================================================================

        // EP01 ray passes through triangle
        Ray ray = new Ray(new Point(3, 3, 2), new Vector(-1, -1, -4));
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(1, 5, 0), new Point(6, 0, 0));
        assertEquals(1, triangle.findIntersections(ray).size());
        assertEquals(new Point(2.5, 2.5, 0), triangle.findIntersections(ray).get(0));

        // EP02 ray misses triangle on one side
        ray = new Ray(new Point(3, 3, 2), new Vector(1, 1, -4));
        assertEquals(null, triangle.findIntersections(ray));

        // EP03 ray misses triangle on two side
        ray = new Ray(new Point(3, 3, 2), new Vector(-5, 5.5, -4));
        assertEquals(null, triangle.findIntersections(ray));

        // Boundary value tests ==============================================================================
        // BV01 ray intersects vertex
        ray = new Ray(new Point(1, 0, 3), new Vector(0, 0, -1));
        assertEquals(null, triangle.findIntersections(ray));

        // BV02 ray intersects edge
        ray = new Ray(new Point(1, 0, 3), new Vector(1, 0, -6));
        assertEquals(null, triangle.findIntersections(ray));

        // BV03 ray intersects edge continuation imaginary line
        ray = new Ray(new Point(0.5, 0, 3), new Vector(0, 0, -1));
        assertEquals(null, triangle.findIntersections(ray));

    }



}