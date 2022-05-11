package primitives;

/**
 * Material class
 */
public class Material {

    /**
     * diffuse coefficient
     */
    public Double3 kD = Double3.ZERO;
    /**
     * kS - specular coefficient
     */
    public Double3 kS = Double3.ZERO;
    /**
     * kR - reflection coefficient
     */
    public Double3 kR = Double3.ZERO;
    /**
     * kT - transparency coefficient
     */
    public Double3 kT = Double3.ZERO;
    /**
     * TODO
     */
    public int nShininess = 0;

    /**
     * setter for kD
     *
     * @param kD new Double3 kD for the material
     * @return this material
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for kD
     *
     * @param kD new double kD for the material
     * @return this material
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter for kS
     *
     * @param kS new Double3 kS for the material
     * @return this material
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for kS
     *
     * @param kS new double kS for the material
     * @return this material
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter for kR
     *
     * @param kR new Double3 kR for the material
     * @return this material
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter for kR
     *
     * @param kR new double kR for the material
     * @return this material
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * setter for kD
     *
     * @param kT new Double3 kT for the material
     * @return this material
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter for kT
     * @param kT new double kT for the material
     *             @return this material
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter for nShininess
     *
     * @param nShininess new nShininess for the material
     * @return this material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
