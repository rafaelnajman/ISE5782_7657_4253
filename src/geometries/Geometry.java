package geometries;

import primitives.*;

/**
 *Interface to shapes extends from it
 */
public interface Geometry {

    /**
     * Function will return normal Vector
     * @param p0 Point from which we want the noemal Vector
     * @return
     */
    public Vector getNormal(Point p0);
}
