package primitives;

/**
 * Material class
 */
public class Material {

    public Double3 kD = new Double3(0, 0, 0), kS = new Double3(0, 0, 0);
    public int nShininess = 0;
    //TODO: CHECK NAME

    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
