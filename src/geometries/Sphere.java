package geometries;

import primitives.Point;
import primitives.Ray;
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
        List<Point> result = null;

        Ray modifiedRay = null;
        Vector v = ray.getP0().subtract(center);
        if (v.dotProduct(v) - radius * radius <= 0){
            modifiedRay = new Ray(ray.getP0().add(ray.getDir().scale(radius)), ray.getDir());
        }
        if (v.dotProduct(v) - radius * radius > 0){
            modifiedRay = ray;
        }
        Vector pointToCenter = center.subtract(modifiedRay.getP0());
        double tm = pointToCenter.dotProduct(modifiedRay.getDir());
        double distanceFromCenter = sqrt(pointToCenter.dotProduct(pointToCenter) - tm * tm);
        if (distanceFromCenter >= radius) {
            return null;
        }
        double th = sqrt(radius * radius - distanceFromCenter * distanceFromCenter);
        double firstDistance = tm - th;
        double secondDistance = tm + th;

        Point firstIntersection = modifiedRay.getP0().add(modifiedRay.getDir().scale(firstDistance));
        Point secondIntersection = modifiedRay.getP0().add(modifiedRay.getDir().scale(secondDistance));


        if (v.dotProduct(v) - radius * radius <= 0){
            return List.of(secondIntersection);
        } else{
            return List.of(firstIntersection, secondIntersection);
        }
    }

}