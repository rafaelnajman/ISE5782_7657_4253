package renderer;

import lighting.LightSource;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.io.*;


import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic class that extends the RayTracer class
 */
public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * function calculates local effects of color on point
     *
     * @param geoPoint  geometry point to color
     * @param ray ray that intersects
     * @return color
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = Color.BLACK;
        Vector vector = ray.getDir();
        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(normal.dotProduct(vector));
        if (nv == 0)
            return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(geoPoint.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            if (nl * nv > 0) {
                if (unshaded(geoPoint, lightVector, normal, lightSource, nv)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)), lightIntensity.scale(calcSpecular(material, normal, lightVector, nl, vector)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates reflected ray and refraction ray
     * @param geoPoint geometry point
     * @param ray ray
     * @return color
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Double3 kr = geoPoint.geometry.getMaterial().kR;
        Double3 kkr = kr.product(k);
        if(kkr > MIN_CALC_COLOR_K ) {

        }
        Ray reflectedRay = constructReflectionRay(geoPoint, geoPoint.geometry.getNormal(geoPoint.point), ray.getDir());
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        if (reflectedPoint != null) {
            color = color.add(calcColor(reflectedPoint, reflectedRay).scale(geoPoint.geometry.getMaterial().kR));
        }
        Ray refractedRay = constructRefractionRay(geoPoint, geoPoint.geometry.getNormal(geoPoint.point), ray.getDir());
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        if (refractedPoint != null) {
            color = color.add(calcColor(refractedPoint, refractedRay).scale(geoPoint.geometry.getMaterial().kT));
        }
        return color;
    }

    /**
     * function calculates specular color
     *
     * @param material    material of geometry
     * @param normal      normal of geometry
     * @param lightVector light vector
     * @param nl          dot product of normal and light vector
     * @param vector      direction of ray
     * @return specular color
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector vector) {
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
        double max = Math.max(0, vector.scale(-1).dotProduct(reflectedVector));
        return material.kS.scale(Math.pow(max, material.nShininess));

    }

    /**
     * function calculates diffusive color
     *
     * @param material material of geometry
     * @param nl       dot product of normal and light vector
     * @return diffusive color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * function calculates color of point
     *
     * @param geoPoint point to color
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray,int level, double k) {
        Color color = geoPoint.geometry.getEmission().add(scene.ambientLight.getIntensity(), calcLocalEffects(geoPoint, ray));
        color = color.add(calcGlobalEffects(geoPoint, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }
    /**
     * function will check if point is unshaded
     *
     * @param geoPoint geometry point to check
     * @param l  light vector
     * @param n  normal vector
     * @return true if unshaded
     */
    private boolean unshaded(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource, double nv) {

        Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections != null) {
            double distance = lightSource.getDistance(geoPoint.point);
            for (GeoPoint intersection : intersections) {
                if (intersection.point.distance(geoPoint.point) < distance)
                    return false;
            }
        }

        return true;
    }

    /**
     * function will construct a reflection ray
     *
     * @param geoPoint     geometry point to check
     * @param normal normal vector
     * @param vector direction of ray to point
     * @return reflection ray
     */
    private Ray constructReflectionRay(GeoPoint geoPoint, Vector normal, Vector vector) {
        Vector reflectedVector = vector.subtract(normal.scale(2 * vector.dotProduct(normal)));
        return new Ray(geoPoint.point, reflectedVector, normal);
    }

    /**
     * function will construct a refraction ray
     *
     * @param geoPoint     geometry point to check
     * @param normal normal vector
     * @param vector direction of ray to point
     * @return refraction ray
     */
    private Ray constructRefractionRay(GeoPoint geoPoint, Vector normal, Vector vector) {
        return new Ray(geoPoint.point, vector, normal);
    }

    /**
     * Find the closest intersection point with a ray.
     * @param ray The ray to checks intersections with.
     * @return The closest intersection point with the ray.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray) {

    }
}