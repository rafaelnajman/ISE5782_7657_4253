package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * This class will represent a ray using a point and a vector
 */
public class Ray {

    final private Point p0;
    final private Vector dir;

    /**
     * Constructor that receives point and vector
     *
     * @param p0  point
     * @param dir Vector direction
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Getter for ray point
     *
     * @return ray point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Getter for ray direction
     *
     * @return ray direction
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Getter for point at a given distance from ray origin
     *
     * @param t distance from ray origin
     * @return point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;
        Ray ray = (Ray) _object;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}



