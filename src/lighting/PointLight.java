package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource {

    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * setter for kC
     *
     * @param _kC - new value for kC
     * @return this PointLight for builder pattern
     */
    public PointLight setkC(double _kC) {
        kC = _kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param _kL - new value for kL
     * @return this PointLight for builder pattern
     */
    public PointLight setkL(double _kL) {
        kL = _kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param _kQ - new value for kQ
     * @return this PointLight for builder pattern
     */
    public PointLight setkQ(double _kQ) {
        kQ = _kQ;
        return this;
    }


    @Override
    public Color getIntensity(Point point) {
        return null;
    }

    @Override
    public Vector getL(Point point) {
        return null;
    }
}
