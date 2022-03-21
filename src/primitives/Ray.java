package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

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

    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * Function returns true if Rays are equal
     * @param _object object we are comparing
     * @return boolean
     */
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



