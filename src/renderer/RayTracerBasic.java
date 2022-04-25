package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * RayTracerBasic class that extends the RayTracer class
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * constructor that calls super constructor
     *
     * @param scene the scene to trace through
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestPoint(intersections));
    }

    /**
     * function calculates color of point
     *
     * @param p0 point to color
     * @return color
     */
    private Color calcColor(Point p0) {
        return scene.ambientLight.getIntensity();
    }
}
