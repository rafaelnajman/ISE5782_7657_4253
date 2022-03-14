package geometries;

import primitives.*;
import static primitives.Util.*;

/**
 * Class will be used to represent a Tube
 */
public class Tube implements Geometry {

    final Ray axisRay;
    final double radius;

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
     * function that returns normal
     *
     * @param p point from which we want to receive normal
     * @return normal
     */
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        double t = p.subtract(p0).dotProduct(dir);
        Point o = isZero(t) ? p0 : p0.add(dir.scale(t));

        return p.subtract(o).normalize();
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
}
