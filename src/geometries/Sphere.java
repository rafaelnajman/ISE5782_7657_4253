package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class will be used to represent a sphere
 */
public class Sphere implements Geometry{

    final private Point center;
    final private double radius;

    /**
     * Constructor for sphere that receives center and radius
     * @param center center point
     * @param radius radius value
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * function that returns center of sphere
     * @return center of sphere
     */

    public Point getCenter() {
        return center;
    }

    /**
     * function that returns radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p0){
        return p0.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
