package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a plane in 3D space with normal vector and a point on the plane
 */
public class Plane implements Geometry{

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
     * Overrides the function of the parent class and calculates the normal vector of the plane
     * @param p0 point on the plane
     * @return normal Vector
     */
    @Override
    public Vector getNormal(Point p0){
        return normal;
    }

    /**
     * returns the normal vector of the plane
     * @return normal Vector
     */
    public Vector getNormal(){
        return normal;
    }
}



