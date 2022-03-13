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
    void testConstructor(){

           assertThrows(IllegalArgumentException.class,
                   () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 0, 2)),
                   "constructed Plane with equal points");
           assertThrows(IllegalArgumentException.class,
                   () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 3), new Point(0, 0, 2)),
                   "constructed plane must have Vectors in different directions");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        Plane p1 = new Plane(new Point(0,0,0), new Point(0,5,0), new Point(5,0,0));
        Vector normal = new Vector(0,0,1);
        assertTrue(normal.equals(p1.getNormal(new Point(1,1,0))) ||
                   normal.equals(p1.getNormal(new Point(-1,-1,0))),"bad normal to plane");
    }
}