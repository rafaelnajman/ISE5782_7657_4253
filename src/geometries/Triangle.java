package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class will be used to represent a triangle
 */
public class Triangle extends Polygon {

    /**
     * Triangle constructor using polygon constructor with 3 points
     *
     * @param p0 first point
     * @param p1 second point
     * @param p2 third point
     */
    public Triangle(Point p0, Point p1, Point p2) {
        super(p0, p1, p2);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = this.plane.findIntersections(ray);
        if (result == null) return null;

        Point p0 = this.vertices.get(0);
        Point p1 = this.vertices.get(1);
        Point p2 = this.vertices.get(2);
        Point p = result.get(0);

        try {
            Vector n1 = p1.subtract(p0).crossProduct(p0.subtract(p));
            Vector n2 = p2.subtract(p1).crossProduct(p1.subtract(p));
            Vector n3 = p0.subtract(p2).crossProduct(p2.subtract(p));
            
            double n1n2 = alignZero(n1.dotProduct(n2));
            if (n1n2 == 0) return null;
            double n2n3 = alignZero(n2.dotProduct(n3));
            if (n1n2 * n2n3 <= 0) return null; // must have same sign
            double n3n1 = alignZero(n3.dotProduct(n1));
            if (n1n2 * n3n1 <= 0) return null; // must have same sign

            return result;

        } catch (IllegalArgumentException ignore) {
            return null;
        }

    }
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = this.plane.findGeoIntersections(ray);
        if (result == null) return null;

        Point p0 = this.vertices.get(0);
        Point p1 = this.vertices.get(1);
        Point p2 = this.vertices.get(2);
        Point p = result.get(0).point;

        try {
            Vector n1 = p1.subtract(p0).crossProduct(p0.subtract(p));
            Vector n2 = p2.subtract(p1).crossProduct(p1.subtract(p));
            Vector n3 = p0.subtract(p2).crossProduct(p2.subtract(p));

            double n1n2 = alignZero(n1.dotProduct(n2));
            if (n1n2 == 0) return null;
            double n2n3 = alignZero(n2.dotProduct(n3));
            if (n1n2 * n2n3 <= 0) return null; // must have same sign
            double n3n1 = alignZero(n3.dotProduct(n1));
            if (n1n2 * n3n1 <= 0) return null; // must have same sign

            return List.of(new GeoPoint(this, result.get(0).point));

        } catch (IllegalArgumentException ignore) {
            return null;
        }
    }
}


