package lighting;

import primitives.*;

/**
 * class PointLight represents a point light source
 */
public class PointLight extends Light implements LightSource {

    private final Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * setter for kC
     *
     * @param _kC - new value for kC
     * @return this PointLight for builder pattern
     */
    public PointLight setKc(double _kC) {
        kC = _kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param _kL - new value for kL
     * @return this PointLight for builder pattern
     */
    public PointLight setKl(double _kL) {
        kL = _kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param _kQ - new value for kQ
     * @return this PointLight for builder pattern
     */
    public PointLight setKq(double _kQ) {
        kQ = _kQ;
        return this;
    }


    @Override
    public Color getIntensity(Point point) {
        double d2 = position.distanceSquared(point);
        return intensity.reduce(kC + kL * Math.sqrt(d2) + kQ * d2);
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
