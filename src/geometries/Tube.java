package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class will be used to represent a Tube
 */
public class Tube implements Geometry{

    final Ray axisRay;
    final double radius;

    /**
     * Constructor for Tube class with a ray and a radius
     * @param axisRay ray value
     * @param radius radius value
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * function that returns normal
     * @param p0 point from which we want to receive normal
     * @return normal
     */
    public Vector getNormal(Point p0){
        return null;
    }

    /**
     * getter function for axisRay
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * function that returns radius value
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }
}
