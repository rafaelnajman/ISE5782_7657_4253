package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * RayTracerBasic class that extends the RayTracer class
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * constructor that calls super constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

@Override
    public Color traceRay(Ray ray){
        List<Point>  intersections = scene.geometries.findIntersections(ray);
        if (intersections == null)
            return scene.background;
        Point p0 = ray.findClosestPoint(intersections);
        return calcColor(p0);

    }

    /**
     * function calculates color of point
     * @param p0 point
     * @return color
     */
    private Color calcColor (Point p0){
        return scene.ambientLight.getIntensity();
    }
}
