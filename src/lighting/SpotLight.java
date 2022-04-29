package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * SpotLight class
 */

public class SpotLight extends PointLight{

    private Vector direction;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(Math.max(0, direction.dotProduct(getL(point))));
    }
}
