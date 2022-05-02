package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Class represents a list of geometries in the scene
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    //use LinkedList instead of ArrayList for better performance in the future (if needed) - O(1) instead of O(n) for add and remove operations
    public Geometries() {
    }

    /**
     * constructor for the class
     * @param geometries list of geometries to add
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add geometries to the list
     * @param geometries list of geometries to add
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //TODO: implement
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findGeoIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(temp);
            }
        }
        return intersections;


    }

}
