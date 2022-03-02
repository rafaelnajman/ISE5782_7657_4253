package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

    Point q0;
    Vector normal;
    public Plane(Point p0, Point p1, Point p2) {
        q0 = p0;
        normal = null;
    }
        public Plane(Point p0, Vector v0){
        q0 = p0;
        normal = v0;
    }

    @Override
    public Vector getNormal(Point p0){
        return null;
    }
    public Vector getNormal(){
        return normal;
    }
}



