package unittests.primitives;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Ray class.
 */
class RayTest {
    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void testFindClosestPoint() {
        Geometries geometries = new Geometries();
        //Equivalence Partitions test ===========================================================

        //EP01: Closes point to the ray is in middle of intersection list
        geometries.add(new Sphere(new Point(5, 0,0), 1));
        geometries.add(new Sphere(new Point(3, 0,0), 1));
        geometries.add(new Sphere(new Point(4, 0,0), 0.5));
        Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        assertEquals(new Point(2,0,0),ray.findClosestPoint(geometries.findIntersections(ray)));

        //Boundary Values test ==================================================================

        //BV01: Closes point to the ray is in the end of intersection list
        geometries.add(new Sphere(new Point(2, 0,0), 1));
        assertEquals(new Point(1,0,0),ray.findClosestPoint(geometries.findIntersections(ray)));

        //BV02: Closes point to the ray is in the start of intersection list
        geometries = new Geometries();
        geometries.add(new Sphere(new Point(3, 0,0), 1));
        geometries.add(new Sphere(new Point(4, 0,0), 1));
        geometries.add(new Sphere(new Point(5, 0,0), 1));
        assertEquals(new Point(2,0,0),ray.findClosestPoint(geometries.findIntersections(ray)));

        //BV03: Intersection list is empty
        geometries = new Geometries();
        assertNull(ray.findClosestPoint(geometries.findIntersections(ray)));
    }
}