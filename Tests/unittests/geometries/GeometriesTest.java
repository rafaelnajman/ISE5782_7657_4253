package unittests.geometries;

import geometries.*;
import primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
    }

    @Test
    void findIntersections() {
        //Equivalence Partitions test ===========================================================
        //EP01 - Ray intersects with a Sphere and plane but not with a Triangle
        Geometries geometries = new Geometries();
        geometries.add(new Sphere(new Point(1, 0, 0), 1));
        geometries.add(new Plane(new Point(1, 0, 0), new Vector(0, 1, 0)));
        geometries.add(new Triangle(new Point(1, 0, 0), new Point(1, 5, 0), new Point(6, 0, 0)));
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0.5, 4, 0.5), new Vector(0, -1, 0))).size(), "Ray intersects with a Sphere and plane but not with a Triangle");

        //Boundary Values test ==================================================================
        //BV01 - Ray does not intersect with any geometries
        assertEquals(null, geometries.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 1, 0))), "Ray does not intersect with any geometries");


        //BV02 - Ray intersects with only one geometry(plane)
        assertEquals(1, geometries.findIntersections(new Ray(new Point(0.5, 4, 3), new Vector(0, -1, 0))).size(), "Ray intersects with only one geometry(plane)");

        //BV03 - Ray intersects with all 3 geometries(4 points)
        assertEquals(4, geometries.findIntersections(new Ray(new Point(1.5, 1, -0.5), new Vector(-1, -4, 3.5))).size(), "Ray intersects with all 3 geometries(4 points)");

        //BV04 - geometries is empty
        geometries = new Geometries();
        assertEquals(null, geometries.findIntersections(new Ray(new Point(0.5, 4, 0.5), new Vector(0, -1, 0))), "geometries is empty");
    }
}