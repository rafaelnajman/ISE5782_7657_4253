package unittests.geometries;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        Tube tube = new Tube (new Ray (new Point (0,0,0), new Vector (0,1,0)), 3);
        Vector v0 = new Vector (0,0,1);
        assertEquals(v0, tube.getNormal(new Point(0,7,3)), "getNormal of tube does not work for normal case");
        assertEquals(v0, tube.getNormal(new Point(0,0,3)), "getNormal of tube does not work for extreme case");
    }
}