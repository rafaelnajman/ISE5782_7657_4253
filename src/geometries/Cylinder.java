package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Class will be used to represent a Cylinder
 */
public class Cylinder extends Tube {

    final private double height;

    /**
     * Constructor for Cylinder
     *
     * @param axisRay central ray of Cylinder
     * @param radius  radius value
     * @param height  height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter function for height
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Cylinder) &&
                super.equals(obj)&&  this.height == ((Cylinder) obj).height;
    }



    @Override
    public Vector getNormal(Point point) {
        //  checks if it in the bases center to avoid zero vector
        if( point.equals(axisRay.getP0() ) ||
                point.equals(axisRay.getP0().add(this.axisRay.getDir().scale(height)) ) ){
            return  this.axisRay.getDir() ;
        }
        // checks if it is in the bases and if it is it returbns axsix dir and if it not calc like tube
        double radiusSquared = this.radius * this.radius;
        Vector toOtherBase = this.axisRay.getDir().scale(this.height) ;
        Point p0Ver2 = this.axisRay.getP0().add(toOtherBase);
        double crossProduct01 =  this.axisRay.getDir().dotProduct(this.axisRay.getP0().subtract(point));
        double crossProduct02 =   this.axisRay.getDir().dotProduct(p0Ver2.subtract(point));
        return  (point.distanceSquared(this.axisRay.getP0()) <= radiusSquared  && crossProduct01  == 0) ||
                (point.distanceSquared(p0Ver2) <= radiusSquared && crossProduct02 == 0) ?
                this.axisRay.getDir() :
                super.getNormal(point) ;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        List<GeoPoint> res = new ArrayList<>();
        List<GeoPoint> lst = super.findGeoIntersectionsHelper(ray);
        if (lst != null)
            for (GeoPoint geoPoint : lst) {
                double distance = Util.alignZero(geoPoint.point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()));
                if (distance > 0 && distance <= height)
                    res.add(geoPoint);
            }

        if (res.size() == 0)
            return null;
        return res;
    }
}