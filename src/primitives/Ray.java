package primitives;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 * This class will represent a ray using a point and a vector
 */
public class Ray {

    //value to move geoPoint, so it does not shade on itself
    private static final double DELTA = 0.1;

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
     * Constructor that moves the ray by DELTA
     * @param p0 point
     * @param direction direction (must be normalized)
     * @param normal normal
     */
    public Ray(Point p0, Vector direction, Vector normal) {
        Vector delta = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : - DELTA);
        this.p0 = p0.add(delta);
        this.dir = direction;
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

    /**
     * Calculate the closest point in list of intersections
     * @param points list of points
     * @return closest point
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty())
            return null;
        Point closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point p : points) {
            double distance = p.distance(p0);
            if (distance < minDistance) {
                closest = p;
                minDistance = distance;
            }
        }
        return closest;
    }

    /**
     * Find closest GeoPoint
     * @param points list of GeoPoints
     * @return closest GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null || points.isEmpty())
            return null;
        GeoPoint closest = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (GeoPoint p : points) {
            double distance = p.point.distance(p0);
            if (distance < minDistance) {
                closest = p;
                minDistance = distance;
            }
        }
        return closest;
    }


    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (!(_object instanceof Ray ray)) return false;
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



