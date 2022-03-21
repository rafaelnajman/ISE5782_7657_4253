package geometries;

import primitives.*;

import java.util.List;

/**
 * Intersectable interface defines the intersection method for all the geometries in the scene
 */
public interface Intersectable {
    /**
     * function that returns a list of all intersections of a ray with the geometry
     * @param ray the ray to check intersection with the geometry object
     * @return a list of all intersections points
     */
    public List<Point> findIntersections(Ray ray);

}
