package lighting;

import primitives.*;

public class AmbientLight {
    private Color intensity;

    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }
    public  AmbientLight(){
        this.intensity = Color.BLACK;
    }

    public Color getIntensity() {
        return intensity;
    }
}
