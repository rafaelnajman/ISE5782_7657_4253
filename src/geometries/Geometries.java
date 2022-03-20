package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries;

    //use LinkedList instead of ArrayList for better performance in the future (if needed) - O(1) instead of O(n) for add and remove operations
    public Geometries() {
        geometries = new LinkedList<>();
    }
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        for (Intersectable geometry : geometries)
            this.geometries.add(geometry);
    }
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries)
            this.geometries.add(geometry);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new LinkedList<>();
        List<Point> temp;
        for (Intersectable geometry : geometries) {
            temp = geometry.findIntersections(ray);
            if (temp != null)
                intersections.addAll(temp);
        }
        if(intersections.size() == 0)
            return null;
        return intersections;
    }

}
