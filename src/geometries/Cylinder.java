package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{

    double height;

    /**
     * construct a cylinder from a tube and a height
     *
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public Vector getNormal(Point p0){
        return null;
    }
}
