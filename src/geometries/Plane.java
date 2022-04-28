package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Plane class represents a plane in 3D space with normal vector and a point on the plane
 */
public class Plane extends Geometry{

    final private Point q0;
    final private Vector normal;

    /**
     * Constructor for plane with 3 points and calculates the normal vector of the plane
     * @param p0 first point
     * @param p1 second point
     * @param p2 third point
     * @throws IllegalArgumentException when the points are on the same line
     */
    public Plane(Point p0, Point p1, Point p2) {
        q0 = p0;
        Vector v1 = p1.subtract(p2);
        Vector v2 = p0.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructor for plane with normal vector and a point on the plane
     * @param p0 point on the plane
     * @param v0 Vector on the plane
     */
    public Plane(Point p0, Vector v0){
        q0 = p0;
        normal = v0.normalize();
    }
    /**
     * returns the normal vector of the plane
     * @return normal Vector
     */

    public Vector getNormal(){
        return normal;
    }

    @Override
    public Vector getNormal(Point p0){
        return normal;
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector u;
        try {
            u = q0.subtract(ray.getP0());
        }catch (IllegalArgumentException ignore) {
            //return null if ray starts at reference point of plane (we do this as to not create a 0 vector)
            return null;
        }

        double denominator = normal.dotProduct(ray.getDir());
        //return null if ray is parallel to plane (orthogonal to normal vector)
        if (Util.isZero(denominator)) return null;

        //calculate distance of point from plane
        double t = Util.alignZero(u.dotProduct(normal) / denominator);
        //return null if point is behind start of ray
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));    }
}



