package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.io.*;


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
        int i;
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections != null)
            if (1 == 1)
                i = 0;
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections));
    }

    /**
     * function calculates color of point
     *
     * @param geoPoint point to color
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint) {
        return geoPoint.geometry.getEmission();
    }
}
