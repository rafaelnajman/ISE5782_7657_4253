package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a plane in 3D space with normal vector and a point on the plane
 */
public class Plane implements Geometry{

    Point q0;
    Vector normal;

    /**
     * constructor for plane with 3 points and calculates the normal vector of the plane
     * @param p0
     * @param p1
     * @param p2
     */
    public Plane(Point p0, Point p1, Point p2) {
        q0 = p0;
        normal = null;
    }
    /**
     * constructor for plane with normal vector and a point on the plane
     * @param p0
     * @param v0
     */
    public Plane(Point p0, Vector v0){
        q0 = p0;
        normal = v0;
    }

    /**
     * overrides the function of the parent class and calculates the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point p0){
        return normal;
    }

    /**
     * returns the normal vector of the plane
     */
    public Vector getNormal(){
        return normal;
    }
}



