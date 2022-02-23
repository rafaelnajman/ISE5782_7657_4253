package primitives;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class Vector extends Point{
    // ***************** Constructors ********************** //
    Vector (double x, double y, double z){
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

    Vector add(Vector _vector){
        return new Vector(this.xyz.add( _vector.xyz));
    }

    Vector scale(double _scale){
        return new Vector(this.xyz.scale(_scale));
    }

    double dotProduct(Vector _vector){
        double finalDotProduct = (this.xyz.d1 - _vector.xyz.d1) * (this.xyz.d2 - _vector.xyz.d2) * (this.xyz.d3 - _vector.xyz.d3);
        if (finalDotProduct == 0) {
            throw new IllegalArgumentException("Dot product cannot be zero");
        }
        return finalDotProduct;
    }

    Vector crossProduct(Vector _vector){
        //error checking needed
        return new Vector(
                this.xyz.d2 * _vector.xyz.d3 - this.xyz.d3 * _vector.xyz.d2,
                this.xyz.d3 * _vector.xyz.d1 - this.xyz.d1 * _vector.xyz.d3,
                this.xyz.d1 * _vector.xyz.d2 - this.xyz.d2 * _vector.xyz.d1
        );
    }
    double lengthSquared(){
        return this.xyz.d1 * this.xyz.d1 + this.xyz.d2 * this.xyz.d2 + this.xyz.d3 * this.xyz.d3;
    }
    double length(){
        return sqrt(lengthSquared());
    }

    Vector normalize(){
        return new Vector(this.xyz.reduce(this.length()));
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }
}


}
