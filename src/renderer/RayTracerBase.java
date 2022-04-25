package renderer;

import primitives.*;
import scene.Scene;

/**
 * class that holds scene and other functions
 */
public abstract class RayTracerBase {
    protected final Scene scene;

    /**
     * function that sets scene
     * @param scene that we will set
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * function trace ray and return color of closest point
     * @param ray ray to trace
     * @return color
     */
    public abstract Color traceRay(Ray ray);
}
