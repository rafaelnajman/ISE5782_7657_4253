package geometries;

import primitives.*;

import java.util.List;

/**
 *Interface to shapes extends from it
 */
public interface Geometry extends Intersectable {
    /**
     * Function will return normal Vector at a point on the geometry body
     * @param p0 at a point on the geometry body
     * @return the normal vector
     */
    public Vector getNormal(Point p0);
}
