package geometries;

import primitives.*;

import java.util.List;

public interface Intersectable {
    /**
     * function that returns a list of all intersections of a ray with the geometry
     * @param ray the ray to check intersection with the geometry object
     * @return a list of all intersections points
     */
    public List<Point> findIntersections(Ray ray);

}
