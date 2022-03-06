package primitives;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class Vector extends Point {

    /**
     * This class extends Point will serve all classes that use Vector parameters
     * It will provide functions that modify the Vector values
     */

    /**
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */

    public Vector (double x, double y, double z){
        super(x,y,z);
        Double3 _point = new Double3(x,y,z);
        if (_point.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     *
     * @param _point Point value to construct Vector
     */

    Vector (Double3 _point){
        super(_point);
        if (_point.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     *
     * @param _vector new Vector we want to add to current one
     * @return New Vector
     */

    public Vector add(Vector _vector){
        return new Vector(this.xyz.add( _vector.xyz));
    }

    /**
     *
     * @param _scale number to multiply Vector
     * @return New Vector
     */

    Vector scale(double _scale){
        return new Vector(this.xyz.scale(_scale));
    }

    /**
     *
     * @param _vector Vector that will be parameter for dot product
     * @return double with the value of the dot product
     */

    public double dotProduct(Vector _vector){
        double finalDotProduct = (this.xyz.d1 * _vector.xyz.d1) +
                (this.xyz.d2 * _vector.xyz.d2) +
                (this.xyz.d3 * _vector.xyz.d3);
        return finalDotProduct;
    }

    /**
     *
     * @param _vector Vector that we will use for cross product
     * @return Vector that is result of cross product
     */

    public Vector crossProduct(Vector _vector){
        return new Vector(
                this.xyz.d2 * _vector.xyz.d3 - this.xyz.d3 * _vector.xyz.d2,
                this.xyz.d3 * _vector.xyz.d1 - this.xyz.d1 * _vector.xyz.d3,
                this.xyz.d1 * _vector.xyz.d2 - this.xyz.d2 * _vector.xyz.d1
        );
    }

    /**
     *
     * @return double containing square of the length
     */

    public double lengthSquared(){
        return this.xyz.d1 * this.xyz.d1 + this.xyz.d2 * this.xyz.d2 + this.xyz.d3 * this.xyz.d3;
    }
    public double length(){
        return sqrt(lengthSquared());
    }

    /**
     *
     * @return Vector of same direction with length = 1
     */

    public Vector normalize(){
        return new Vector(this.xyz.reduce(this.length()));
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }
}

