package primitives;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class Vector extends Point {

    // ***************** Constructors ********************** //
    public Vector (double x, double y, double z){
        super(x,y,z);
        Double3 _point = new Double3(x,y,z);
        if (_point.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }
    Vector (Double3 _point){
        super(_point);
        if (_point.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    public Vector add(Vector _vector){
        return new Vector(this.xyz.add( _vector.xyz));
    }

    Vector scale(double _scale){
        return new Vector(this.xyz.scale(_scale));
    }

    public double dotProduct(Vector _vector){
        double finalDotProduct = (this.xyz.d1 - _vector.xyz.d1) * (this.xyz.d2 - _vector.xyz.d2) * (this.xyz.d3 - _vector.xyz.d3);
        return finalDotProduct;
    }

    public Vector crossProduct(Vector _vector){
        return new Vector(
                this.xyz.d2 * _vector.xyz.d3 - this.xyz.d3 * _vector.xyz.d2,
                this.xyz.d3 * _vector.xyz.d1 - this.xyz.d1 * _vector.xyz.d3,
                this.xyz.d1 * _vector.xyz.d2 - this.xyz.d2 * _vector.xyz.d1
        );
    }
    public double lengthSquared(){
        return this.xyz.d1 * this.xyz.d1 + this.xyz.d2 * this.xyz.d2 + this.xyz.d3 * this.xyz.d3;
    }
    public double length(){
        return sqrt(lengthSquared());
    }

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

