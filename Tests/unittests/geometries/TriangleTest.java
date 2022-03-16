package unittests.geometries;

import geometries.Plane;
import geometries.Polygon;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

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

}