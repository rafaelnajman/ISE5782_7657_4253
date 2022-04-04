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

    /**
     * function that gets the position of the camera
     * @return the position
     * */
    public Point getPosition() {
        return position;
    }

    /**
     * function that gets the vTo vector
     * @return the vTo vector
     * */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     * @return the vUp vector
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     * @return the vRight vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * function that gets the distance
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * function that gets the height
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * function that gets the width
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * function that constructs the camera
     * @param _position the position
     * @param _vTo the vTo vector
     * @param _vUp the vUp vector
     *
     */
    public Camera(Point _position, Vector _vTo, Vector _vUp) {
        if(_vTo.dotProduct(_vUp) != 0)
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        position = _position;
        vTo = _vTo.normalize();
        vUp = _vUp.normalize();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * function that sets the width and height
     * @param width
     * @param height
     * @return this
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * function that sets the distance
     * @param distance
     * @return this
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * function that gets the ray from the camera to the point
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i the x coordinate
     * @param j the y coordinate
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point pC = position.add(vTo.scale(distance));

        double rY = height/nY;
        double rX = width/nX;

        double Yi = -(i - (nY - 1d) / 2) * rY;
        double Xj = (j - (nX - 1d) / 2) * rX;
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
