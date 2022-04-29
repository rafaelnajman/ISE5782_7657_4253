package geometries;

import primitives.*;

import java.util.List;

/**
 * Interface that shapes extend from it
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Function will return normal Vector at a point on the geometry body
     *
     * @param p0 at a point on the geometry body
     * @return the normal vector
     */
    public abstract Vector getNormal(Point p0);

    /**
     * Function will return the color of the geometry
     *
     * @return the color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Function will return the material of the geometry
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Function will set the color of the geometry
     */
    public Geometry setEmission(Color _emission) {
        emission = _emission;
        return this;
    }

    /**
     * Function will set the material of the geometry
     */
    public Geometry setMaterial(Material _material) {
        material = _material;
        return this;
    }


}
