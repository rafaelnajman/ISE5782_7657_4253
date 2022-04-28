package lighting;

import primitives.*;


public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    @Override
    public Color getIntensity(Point point) {
        return null;
    }

    @Override
    public Vector getL(Point point) {
        return direction;
    }
}
