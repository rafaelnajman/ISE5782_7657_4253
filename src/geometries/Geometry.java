package geometries;

import primitives.*;

/**
 *Interface to shapes extends from it
 */
public interface Geometry {

    /**
     * Function will return normal Vector at a point on the geometry body
     * @param p0 at a point on the geometry body
     * @return the normal vector
     */
    public Vector getNormal(Point p0);
}
