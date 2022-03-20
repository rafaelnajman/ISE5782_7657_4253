package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Class will be used to represent a sphere
 */
public class Sphere implements Geometry {

    final private Point center;
    final private double radius;

    /**
     * Constructor for sphere that receives center and radius
     *
     * @param center center point
     * @param radius radius value
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * function that returns center of sphere
     *
     * @return center of sphere
     */

    public Point getCenter() {
        return center;
    }

    /**
     * function that returns radius
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p0) {
        return p0.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        if (ray.getP0().equals(center)) {
            List<Point> intersections = new java.util.ArrayList<Point>();
            intersections.add(center.add(ray.getDir().scale(radius)));
            return intersections;
        }
        Vector pointToCenter = center.subtract(ray.getP0());
        double tm = pointToCenter.dotProduct(ray.getDir());
        double distanceFromCenter = sqrt(pointToCenter.dotProduct(pointToCenter) - tm * tm);
        if (distanceFromCenter >= radius) {
            return null;
        }
        double th = sqrt(radius * radius - distanceFromCenter * distanceFromCenter);
        double firstDistance = tm - th;
        double secondDistance = tm + th;
        if (firstDistance > 0 || secondDistance > 0) {
            List<Point> intersections = new java.util.ArrayList<Point>();
            if (Util.alignZero(firstDistance) > 0) {
                Point firstIntersection = ray.getPoint(firstDistance);
                intersections.add(firstIntersection);
            }
            if (Util.alignZero(secondDistance) > 0) {
                Point secondIntersection = ray.getPoint(secondDistance);
                intersections.add(secondIntersection);
            } else {
                return null;
            }
            return intersections;
        }
        return null;
    }
}