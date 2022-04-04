package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    private Point position;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;
    private double height;
    private double width;


    public Point getPosition() {
        return position;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getDistance() {
        return distance;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Camera(Point _position, Vector _vTo, Vector _vUp) {
        if(_vTo.dotProduct(_vUp) != 0)
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        position = _position;
        vTo = _vTo.normalize();
        vUp = _vUp.normalize();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i){
        Point pC = position.add(vTo.scale(distance));

        double rY = height/nY;
        double rX = width/nX;

        double Yi = -(i - (rY - 1)/2) * rY;
        double Xj = (j - (rX - 1) / 2) * rX;
        Point Pij = pC;

        if(Yi != 0) Pij = Pij.add(vUp.scale(Yi));
        if(Xj != 0) Pij = Pij.add(vRight.scale(Xj));

        try{
            return new Ray(position, Pij.subtract(position));
        }catch (Exception e)
        {
            throw e;
        }
    }

}
