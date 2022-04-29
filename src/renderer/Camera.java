package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

/**
 * camera class
 */
public class Camera {
    private Point position;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;
    private double height;
    private double width;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;


    /**
     * function that gets the position of the camera
     *
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * function that gets the vTo vector
     *
     * @return the vTo vector
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     *
     * @return the vUp vector
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     *
     * @return the vRight vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * function that gets the distance
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * function that gets the height
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * function that gets the width
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * function that constructs the camera
     *
     * @param _position the position
     * @param _vTo      the vTo vector
     * @param _vUp      the vUp vector
     */
    public Camera(Point _position, Vector _vTo, Vector _vUp) {
        if (_vTo.dotProduct(_vUp) != 0)
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        position = _position;
        vTo = _vTo.normalize();
        vUp = _vUp.normalize();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * function that sets the width and height
     *
     * @param width  of the view plane
     * @param height of the view plane
     * @return this
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * function that sets the distance
     *
     * @param distance value to set
     * @return this
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * function that sets imageWriter
     *
     * @param imageWriter object to set
     * @return camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * function that sets the rayTracer
     *
     * @param rayTracer object to set
     * @return camera itself
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * function that gets the ray from the camera to the point
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = position.add(vTo.scale(distance));

        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1d) / 2) * rY;
        double jX = (j - (nX - 1d) / 2) * rX;
        Point Pij = pC;

        if (yI != 0) Pij = Pij.add(vUp.scale(yI));
        if (jX != 0) Pij = Pij.add(vRight.scale(jX));

        return new Ray(position, Pij.subtract(position));
    }

    /**
     * function that gets the color of the pixel and renders in to image
     */
    public Camera renderImage() {
        if (position == null || vTo == null || vUp == null || vRight == null || distance == 0 || height == 0 || width == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
                imageWriter.writePixel(j, i, this.castRay(imageWriter.getNx(), imageWriter.getNy(), i, j));
            }
        }
        return this;
    }

    /**
     * function that prints grid on top of image
     *
     * @param interval of grid
     * @param color    of grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if ((i % interval == 0) || (j % interval == 0))
                    imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     * function that calls write to image function
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        imageWriter.writeToImage();
    }

    /**
     * function that casts ray and returns color
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the color
     */
    private Color castRay(int nX, int nY, int i, int j) {
        Ray tempRay = constructRay(nX, nY, j, i);
        return rayTracer.traceRay(tempRay);

    }
}
