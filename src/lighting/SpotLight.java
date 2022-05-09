package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * SpotLight class
 */

public class SpotLight extends PointLight {

    private final Vector direction;
    private double narrowBeam = 1;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * setter for narrowBeam
     *
     * @param _narrowBeam the new value for narrowBeam
     * @return this light
     */
    public SpotLight setNarrowBeam(double _narrowBeam) {
        this.narrowBeam = _narrowBeam;
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        double factor = direction.dotProduct(getL(point));
        if (alignZero(factor) <= 0) return Color.BLACK;
        //check if it is flashlight
        if (narrowBeam != 1) factor = Math.pow(factor, narrowBeam);
        return super.getIntensity(point).scale(factor);
    }
}
