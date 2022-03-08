package geometries;

import primitives.*;

/**
 * class will be abstract and shapes will extend it
 */
public interface Geometry {

    /**
     * Function will return normal Vector
     * @param p0 Point from which we want the noemal Vector
     * @return
     */
    public Vector getNormal(Point p0);
}
