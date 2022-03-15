package primitives;
import static java.lang.Math.sqrt;

/**
 * This class will serve all classes that use _point parameters
 * It will provide functions that modify the _point values
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
     * @param xyz values of the _point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Function receives two points and returns vector connecting both points
     * @param p _point to subtract with this class _point
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * function that returns the addition of two points
     * @param _vector Vector that we want to add to the _point
     * @return new Point after adding Vector
     */
    public Point add(Vector _vector) {
        return new Point(xyz.add(_vector.xyz));
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * function checks if object is equal to this _point
     * @param _object object we compare to see if equals
     * @return boolean confirming equality
     */
    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;

        Point _point = (Point) _object;

        return xyz.equals(_point.xyz);
    }

    /**
     * function determines distance between two points
     * @param _point other _point
     * @return distance
     */
    public double distance(Point _point) {
        return sqrt(this.distanceSquared(_point));
    }

    /**
     * function determines squared distance between two points
     * @param _point other _point
     * @return distance squared
     */
    public double distanceSquared(Point _point){
        double dx = this.xyz.d1 - _point.xyz.d1;
        return dx * dx + Math.pow((this.xyz.d2 - _point.xyz.d2), 2) +
                            Math.pow((this.xyz.d3 - _point.xyz.d3),2);
    }
}

