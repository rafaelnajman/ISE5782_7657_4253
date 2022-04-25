package lighting;

import primitives.*;

/**
 * class AmbientLight
 */
public class AmbientLight {
    private final Color intensity;

    /**
     * constructor that receives a color and sets the intensity based in kA
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * default constructor that sets the intensity to black
     */
    public AmbientLight(){
        this.intensity = Color.BLACK;
    }

    /**
     * function that returns the intensity of the ambient light
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }
}
