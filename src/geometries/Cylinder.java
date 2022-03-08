package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class will be used to represent a Cylinder
 */
public class Cylinder extends Tube{

    final double height;

    /**
     * Constructor for Cylinder
     * @param axisRay central ray of Cylinder
     * @param radius radius value
     * @param height height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * function that returns normal
     * @param p0 point from which we want to receive normal
     * @return normal
     */
    public Vector getNormal(Point p0){
        return null;
    }
}
