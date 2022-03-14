package primitives;
import static java.lang.Math.sqrt;

/**
 * This class will serve all classes that use point parameters
 * It will provide functions that modify the point values
 */
public class Point {

    final protected Double3 xyz;

    /**
     * Constructor to initialize Point based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize Point based object with Double parameter
     * @param xyz values of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Function receives two points and returns vector connecting both points
     * @param p point to subtract with this class point
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * function that returns the addition of two points
     * @param v Vector that we want to add to the point
     * @return new Point after adding Vector
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * function checks if object is equal to this point
     * @param o object we compare to see if equals
     * @return boolean confirming equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return xyz.equals(point.xyz);
    }

    /**
     * function determines distance between two points
     * @param point other point
     * @return distance
     */
    public double distance(Point point) {
        return sqrt(this.distanceSquared(point));
    }

    /**
     * function determines squared distance between two points
     * @param point other point
     * @return distance squared
     */
    public double distanceSquared(Point point){
        double dx = this.xyz.d1 - point.xyz.d1;
        return dx * dx + Math.pow((this.xyz.d2 - point.xyz.d2), 2) +
                            Math.pow((this.xyz.d3 - point.xyz.d3),2);
    }
}

