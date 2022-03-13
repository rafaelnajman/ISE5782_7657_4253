package primitives;

import java.util.Objects;

/**
 * This class will represent a ray using a point and a vector
 */
public class Ray {

    final Point p0;
    final Vector dir;

    /**
     * Constructor that receives point and vector
     * @param p0 point
     * @param dir Vector direction
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    /**
     * Function returns true if Rays are equal
     * @param o object we are comparing
     * @return boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Function returns string representation of Ray
     * @return String
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}



