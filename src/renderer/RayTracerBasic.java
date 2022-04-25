package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    //trace ray and return color
    public Color traceRay(Ray ray){
        List<Point>  intersections = scene.geometries.findIntersections(ray);
        if (intersections == null)
            return scene.background;
        Point p0 = ray.findClosestPoint(intersections);
        return calcColor(p0);

    }

    private Color calcColor (Point p0){
        return scene.ambientLight.getIntensity();
    }
}
