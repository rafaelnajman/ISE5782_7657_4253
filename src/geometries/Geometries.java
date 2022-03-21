package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    //use LinkedList instead of ArrayList for better performance in the future (if needed) - O(1) instead of O(n) for add and remove operations
    public Geometries() {
    }
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(temp);
            }
        }
        return intersections;
    }

}
