package primitives;

public class Point {
    Double3 xyz;

    // ***************** Constructors ********************** //
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }
    Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }
}

