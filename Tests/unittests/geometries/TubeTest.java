package unittests.geometries;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Tube class
 */
class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // Equivalence Partitions tests ======================================================================
        // EP01 get normal of regular point on tube
        Tube tube = new Tube (new Ray (new Point (0,0,0), new Vector (0,1,0)), 3);
        Vector v0 = new Vector (0,0,1);
        assertEquals(v0, tube.getNormal(new Point(0,7,3)), "getNormal of tube does not work for normal case");

        // Boundary Value Analysis tests ======================================================================
        // BV01 point is orthogonal to direction
        assertEquals(v0, tube.getNormal(new Point(0,0,3)), "getNormal of tube does not work for extreme case");
    }
}