package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class will be used to represent a Cylinder
 */
public class Cylinder extends Tube{

    final private double height;

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
     * @param p point from which we want to receive normal
     * @return normal
     */
    @Override
    public Vector getNormal(Point p){
        return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
