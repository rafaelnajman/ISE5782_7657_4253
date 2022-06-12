package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

/**
 * camera class
 */
public class Camera {
    private Point position;//camera location
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;
    private double height;
    private double width;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int antiAliasingFactor = 1;
    private int maxAdaptiveLevel = 2;
    private boolean useAdaptive = false;


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
    public Vector getVTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     *
     * @return the vUp vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     *
     * @return the vRight vector
     */
    public Vector getVRight() {
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
     * @param position the position
     * @param vTo      the vTo vector
     * @param vUp      the vUp vector
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        if (vTo.dotProduct(vUp) != 0)
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        this.position = position;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp).normalize();
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
     * function that sets the antiAliasingFactor
     *
     * @param antiAliasingFactor value to set
     * @return camera itself
     */
    public Camera setAntiAliasingFactor(int antiAliasingFactor) {
        this.antiAliasingFactor = antiAliasingFactor;
        return this;
    }

    /**
     * setter for UseAdaptive
     * @param useAdaptive- the number of pixels in row/col of every pixel
     * @return camera itself
     */
    public Camera setUseAdaptive(boolean useAdaptive) {
        this.useAdaptive = useAdaptive;
        return this;
    }

    /**
     * setter for maxAdaptiveLevel
     * @param maxAdaptiveLevel- The depth of the recursion
     * @return camera itself
     */
    public Camera setMaxAdaptiveLevel(int maxAdaptiveLevel) {
        this.maxAdaptiveLevel = maxAdaptiveLevel;
        return this;
    }

    /**
     * function that calculates the pixels location
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    private Point findPixelLocation(int nX, int nY, int j, int i) {

        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1d) / 2) * rY;
        double jX = (j - (nX - 1d) / 2) * rX;
        Point pIJ = position.add(vTo.scale(distance));

        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        if (jX != 0) pIJ = pIJ.add(vRight.scale(jX));
        return pIJ;
    }

    /**
     * function that returns the ray from the camera to the point
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return new Ray(position, findPixelLocation(nX, nY, j, i).subtract(position));
    }

    /**
     * function that returns the rays from the camera to the point
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();
        Point centralPixel = findPixelLocation(nX, nY, j, i);
        double rY = height / nY / antiAliasingFactor;
        double rX = width / nX / antiAliasingFactor;
        double x, y;

        for (int rowNumber = 0; rowNumber < antiAliasingFactor; rowNumber++) {
            for (int colNumber = 0; colNumber < antiAliasingFactor; colNumber++) {
                y = -(rowNumber - (antiAliasingFactor - 1d) / 2) * rY;
                x = (colNumber - (antiAliasingFactor - 1d) / 2) * rX;
                Point pIJ = centralPixel;
                if (y != 0) pIJ = pIJ.add(vUp.scale(y));
                if (x != 0) pIJ = pIJ.add(vRight.scale(x));
                rays.add(new Ray(position, pIJ.subtract(position)));
            }
        }
        return rays;
    }


    /**
     * function that gets the color of the pixel and renders in to image
     */
    public Camera renderImage() {
        if (position == null || vTo == null || vUp == null || vRight == null || distance == 0 || height == 0 || width == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++)
            for (int j = 0; j < nY; j++)
                imageWriter.writePixel(j, i, this.castRay(nX, nY, j, i));
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

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
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
     * @param j  the x coordinate
     * @param i  the y coordinate
     * @return the color
     */
    private Color castRay(int nX, int nY, int i, int j) {
        if (useAdaptive)
            return adaptiveHelper(findPixelLocation(nX, nY, i, j), nX, nY);
        else if (antiAliasingFactor == 1)
            return rayTracer.traceRay(constructRay(nX, nY, i, j));
        else
            return rayTracer.traceRays(constructRays(nX, nY, i, j));
    }

    /**
     * get the point and return the color of the ray to this point
     *
     * @param p- point on the view plane
     * @return color of this point
     */
    private Color calcPointColor(Point p) {
        return rayTracer.traceRay(new Ray(position, p.subtract(position)));
    }

    /**
     * calculate average color of the pixel by using adaptive Super-sampling
     *
     * @param center- the center of the pixel
     * @param nY-     number of pixels to width
     * @param nX-     number of pixels to length
     * @return- the average color of the pixel
     */
    private Color adaptiveHelper(Point center, double nY, double nX) {
        Hashtable<Point, Color> pointColorTable = new Hashtable<Point, Color>();
        double rY = height / nY / 2;
        double rX = width / nX / 2;
        Color upRight = calcPointColor(center.add(vUp.scale(rY)).add(vRight.scale(rX)));
        Color upLeft = calcPointColor(center.add(vUp.scale(rY)).add(vRight.scale(-rX)));
        Color downRight = calcPointColor(center.add(vUp.scale(-rY)).add(vRight.scale(rX)));
        Color downLeft = calcPointColor(center.add(vUp.scale(-rY)).add(vRight.scale(-rX)));

        return adaptive(1, center, rX, rY, pointColorTable, upLeft, upRight, downLeft, downRight);
    }

    /**
     * recursive method that return the average color of the pixel- by checking the color of the four corners
     *
     * @param max-         the depth of the recursion
     * @param center-      the center of the pixel
     * @param rX-          the width of the pixel
     * @param rY-          the height of the pixel
     * @param upLeftCol-   the color of the vUp left corner
     * @param upRightCol-  the color of the vUp vRight corner
     * @param downLeftCol- the color of the down left corner
     * @param downRightCol - the color of the down vRight corner
     * @return the average color of the pixel
     */
    private Color adaptive(int max, Point center, double rX, double rY, Hashtable<Point, Color> pointColorTable,
                           Color upLeftCol, Color upRightCol, Color downLeftCol, Color downRightCol) {
        if (max == maxAdaptiveLevel) {
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
        if (upRightCol.equals(upLeftCol) && downRightCol.equals(downLeftCol) && downLeftCol.equals(upLeftCol))
            return upRightCol;
        else {
            Color rightPCol = getPointColorFromTable(center.add(vRight.scale(rX)), pointColorTable);
            Color leftPCol = getPointColorFromTable(center.add(vRight.scale(-rX)), pointColorTable);
            Color upPCol = getPointColorFromTable(center.add(vUp.scale(rY)), pointColorTable);
            Color downPCol = getPointColorFromTable(center.add(vUp.scale(-rY)), pointColorTable);
            Color centerCol = calcPointColor(center);

            rX = rX / 2;
            rY = rY / 2;
            upLeftCol = adaptive(max + 1, center.add(vUp.scale(rY / 2)).add(vRight.scale(-rX / 2)), rX, rY, pointColorTable,
                    upLeftCol, upPCol, leftPCol, centerCol);
            upRightCol = adaptive(max + 1, center.add(vUp.scale(rY / 2)).add(vRight.scale(rX / 2)), rX, rY, pointColorTable,
                    upPCol, upRightCol, centerCol, leftPCol);
            downLeftCol = adaptive(max + 1, center.add(vUp.scale(-rY / 2)).add(vRight.scale(-rX / 2)), rX, rY, pointColorTable,
                    leftPCol, centerCol, downLeftCol, downPCol);
            downRightCol = adaptive(max + 1, center.add(vUp.scale(-rY / 2)).add(vRight.scale(rX / 2)), rX, rY, pointColorTable,
                    centerCol, rightPCol, downPCol, downRightCol);
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
    }

    /**
     * check if this point exist in the HashTable return his color otherwise calculate the color and save it
     *
     * @param point-           certain point in the pixel
     * @param pointColorTable- dictionary that save points and their color
     * @return the color of the point
     */
    private Color getPointColorFromTable(Point point, Hashtable<Point, Color> pointColorTable) {
        if (!(pointColorTable.containsKey(point))) {
            Color color = calcPointColor(point);
            pointColorTable.put(point, color);
            return color;
        }
        return pointColorTable.get(point);
    }
}
