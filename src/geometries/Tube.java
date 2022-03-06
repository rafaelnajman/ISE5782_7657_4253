package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents a tube with a radius and a height
 */
public class Tube implements Geometry{

    Ray axisRay;
    double radius;

    /**
     * Constructor for Tube class with a ray and a radius value as parameters and sets the axisRay and radius values to the parameters values respectively
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Vector getNormal(Point p0){
        return null;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }
}
