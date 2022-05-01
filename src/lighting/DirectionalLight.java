package lighting;

import primitives.*;

/**
 * DirectionalLight class
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point point) {
        return direction;
    }
}
