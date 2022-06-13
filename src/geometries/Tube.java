package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 * Class will be used to represent a Tube
 */
public class Tube extends Geometry {

    final protected Ray axisRay;
    final protected double radius;

    /**
     * Constructor for Tube class with a ray and a radius
     *
     * @param axisRay ray value
     * @param radius  radius value
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * getter function for axisRay
     *
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * function that returns radius value
     *
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {
        double t  =  axisRay.getDir().dotProduct(
                point.subtract(
                        axisRay.getP0()));
        Point O = ( t == 0 ?axisRay.getP0() :  axisRay.getP0().add(
                axisRay.getDir().scale(t)));
        return point.subtract(O).normalize();
    }

    @Override
    public boolean equals(Object obj) {//checks if equals
        return (obj instanceof Tube) && this.axisRay.equals(((Tube) obj).axisRay) &&  this.radius == ((Tube) obj).radius;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector dir = ray.getDir();
        Vector v = axisRay.getDir();
        double dirV = dir.dotProduct(v);

        if (ray.getP0().equals(axisRay.getP0())) { // In case the ray starts on the p0.
            if (Util.isZero(dirV))
                return List.of(new Intersectable.GeoPoint(this, ray.getPoint(radius)));

            if (dir.equals(v.scale(dir.dotProduct(v))))
                return null;


            return List.of(new Intersectable.GeoPoint(this, ray.getPoint(
                    Math.sqrt(radius * radius / dir.subtract(v.scale(dir.dotProduct(v))).lengthSquared()))));


        }
        Vector deltaP = ray.getP0().subtract(axisRay.getP0());
        double dpV = deltaP.dotProduct(v);

        double a = 1 - dirV * dirV;
        double b = 2 * (dir.dotProduct(deltaP) - dirV * dpV);
        double c = deltaP.lengthSquared() - dpV * dpV - radius * radius;

        if (Util.isZero(a)) {
            if (Util.isZero(b)) { // If a constant equation.
                return null;
            }
            return List.of(new Intersectable.GeoPoint(this,ray.getPoint(-c / b))); // if it's linear, there's a solution.
        }

        double discriminant = Util.alignZero(b * b - 4 * a * c);

        if (discriminant < 0) // No real solutions.
            return null;

        double t1 = Util.alignZero(-(b + Math.sqrt(discriminant)) / (2 * a)); // Positive solution.
        double t2 = Util.alignZero(-(b - Math.sqrt(discriminant)) / (2 * a)); // Negative solution.

        if (discriminant <= 0) // No real solutions.
            return null;

        if (t1 > 0 && t2 > 0) {
            List<GeoPoint> points = new LinkedList<>();
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t1)));
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t2)));
            return points;
        }
        else if (t1 > 0) {
            List<GeoPoint> points = new LinkedList<>();
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t1)));
            return  points;
        }
        else if (t2 > 0) {
            List<GeoPoint> points = new LinkedList<>();
            points.add(new Intersectable.GeoPoint(this,ray.getPoint(t2)));
            return points;
        }
        return null;
    }

}