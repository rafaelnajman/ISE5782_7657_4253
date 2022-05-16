package geometries;

import primitives.*;

import java.util.List;

/**
 * Intersectable abstract class defines the intersection method for all the geometries in the scene
 */
public abstract class Intersectable {
    /**
     * function that returns a list of all intersections of a ray with the geometry
     *
     * @param ray the ray to check intersection with the geometry object
     * @return a list of all intersections points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * helper class to connect point to Geometry
     */
    public static class GeoPoint {
        /**
         * the geometry
         */
        public Geometry geometry;
        /**
         * the point
         */
        public Point point;

        /**
         * constructor of the helper class
         *
         * @param geometry to set
         * @param point    to set
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
