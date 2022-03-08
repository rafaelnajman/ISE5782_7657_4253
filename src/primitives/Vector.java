package primitives;

import java.util.Objects;
import static java.lang.Math.sqrt;

/**
 * This class extends Point will serve all classes that use Vector parameters
 * It will provide functions that modify the Vector values
 */
public class Vector extends Point {

    /**
     * Constructor that receives 3 values for Vector
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * Constructor that receives Point
     * @param _point Point value to construct Vector
     */
    Vector(Double3 _point) {
        super(_point);
        if (_point.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Function that adds one Vector to the other
     * @param _vector new Vector we want to add to current one
     * @return New Vector
     */
    public Vector add(Vector _vector) {
        return new Vector(this.xyz.add(_vector.xyz));
    }

    /**
     * Function that will scale Vector
     * @param _scale number to multiply Vector
     * @return New Vector
     */
    Vector scale(double _scale) {
        return new Vector(this.xyz.scale(_scale));
    }

    /**
     * Function return dot product of two Vectors
     * @param _vector Vector that will be parameter for dot product
     * @return double with the value of the dot product
     */
    public double dotProduct(Vector _vector) {
        return (this.xyz.d1 * _vector.xyz.d1) +
                (this.xyz.d2 * _vector.xyz.d2) +
                (this.xyz.d3 * _vector.xyz.d3);
    }

    /**
     * Function returns cross product of two vectors
     * @param _vector Vector that we will use for cross product
     * @return Vector that is result of cross product
     */
    public Vector crossProduct(Vector _vector) {
        return new Vector(
                this.xyz.d2 * _vector.xyz.d3 - this.xyz.d3 * _vector.xyz.d2,
                this.xyz.d3 * _vector.xyz.d1 - this.xyz.d1 * _vector.xyz.d3,
                this.xyz.d1 * _vector.xyz.d2 - this.xyz.d2 * _vector.xyz.d1
        );
    }

    /**
     * Function calculates length squared
     * @return double containing square of the length
     */
    public double lengthSquared() {
        return  dotProduct(this);
    }

    public double length() {
        return sqrt(lengthSquared());
    }

    /**
     * Function returns normalized Vector
     * @return Vector of same direction with length = 1
     */
    public Vector normalize() {
        return new Vector(this.xyz.reduce(this.length()));
    }

    /**
     * Function returns true if Vectors are equal
     * @param obj object we want to check
     * @return boolean
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Function returns object in string format
     * @return string of object
     */
    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }


}

