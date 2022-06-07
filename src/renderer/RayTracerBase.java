package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

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
     * function trace ray and return color of the closest point
     * @param ray ray to trace
     * @return color
     */
    public abstract Color traceRay(Ray ray);

    /**
     * function that traces rays and return average color of pixel
     * @param rays rays to trace
     */
    public abstract Color traceRays(List<Ray> rays);

}
