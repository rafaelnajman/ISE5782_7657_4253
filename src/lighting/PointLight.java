package lighting;

import primitives.*;

/**
 * class PointLight represents a point light source
 */
public class PointLight extends Light implements LightSource {

    private final Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     * @param position  the light's position.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * setter for kC
     *
     * @param kC - new value for kC
     * @return this PointLight for builder pattern
     */
    @SuppressWarnings("unused")
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param kL - new value for kL
     * @return this PointLight for builder pattern
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param kQ - new value for kQ
     * @return this PointLight for builder pattern
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
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
