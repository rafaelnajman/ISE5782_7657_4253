package primitives;

/**
 * Material class
 */
public class Material {

    /**
     * kD - diffuse coefficient
     * kS - specular coefficient
     * kR - reflection coefficient
     * kT - transparency coefficient
     */
    public Double3  kD = new Double3(0, 0, 0),
                    kS = new Double3(0, 0, 0),
                    kR = new Double3(0, 0, 0),
                    kT = new Double3(0, 0, 0);

    public int nShininess = 0;

    /**
     * setter for kD
     * @param kD new Double3 kD for the material
     * @return this material
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for kD
     * @param kD new double kD for the material
     * @return this material
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter for kS
     * @param kS new Double3 kS for the material
     * @return this material
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for kS
     * @param kS new double kS for the material
     * @return this material
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter for kR
     * @param kR new Double3 kR for the material
     * @return this material
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter for kR
     * @param kR new double kR for the material
     * @return this material
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * setter for nShininess
     * @param nShininess new nShininess for the material
     * @return this material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
