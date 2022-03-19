package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.sqrt;

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
        Vector v = ray.getP0().subtract(center);
        if(v.dotProduct(v) - radius * radius > 0){

            Vector pointToCenter = center.subtract(ray.getP0());
            double tm = pointToCenter.dotProduct(ray.getDir());
            double distanceFromCenter = sqrt(pointToCenter.dotProduct(pointToCenter) - tm *tm);
            if(distanceFromCenter >= radius){
                return null;
            }

            double th = sqrt(radius * radius - distanceFromCenter * distanceFromCenter);

            double firstDistance = tm - th;
            double secondDistance = tm + th;

            Point firstIntersection = ray.getP0().add(ray.getDir().scale(firstDistance));
            Point secondIntersection = ray.getP0().add(ray.getDir().scale(secondDistance));

            return List.of(firstIntersection, secondIntersection);
        }
        return null;
    }
}
