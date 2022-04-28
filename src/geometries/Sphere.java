package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

/**
 * Class will be used to represent a sphere
 */
public class Sphere extends Geometry {

    final private Point center;
    final private double radius;
    final private double radiusSquared;

    /**
     * Constructor for sphere that receives center and radius
     *
     * @param center center point
     * @param radius radius value
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radiusSquared = radius * radius;
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        Vector pointToCenter;
        try {
            pointToCenter = center.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }

        double tm = pointToCenter.dotProduct(ray.getDir());
        double distanceFromCenterSquared = pointToCenter.dotProduct(pointToCenter) - tm * tm;
        double thSquared = radiusSquared - distanceFromCenterSquared;
        //check that ray crosses area of sphere, if not then return null
        if (alignZero(thSquared) <= 0) return null;

        double th = sqrt(thSquared);
        double secondDistance = tm + th;
        if (alignZero(secondDistance) <= 0) return null;
        double firstDistance = tm - th;
        return firstDistance <= 0 ? List.of(new GeoPoint(this, ray.getPoint(secondDistance))) //
                : List.of(new GeoPoint(this, ray.getPoint(firstDistance)), new GeoPoint(this, ray.getPoint(secondDistance)));
    }
}