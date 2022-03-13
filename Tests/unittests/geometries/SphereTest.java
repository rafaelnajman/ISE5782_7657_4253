package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    @Test
    void getNormal() {
        Sphere s1 = new Sphere (new Point (0,0,0), 1);
        Vector normal = new Vector(1,0,0);
        assertEquals(normal, s1.getNormal(new Point(1,0,0)), "Bad normal for sphere");
    }
}