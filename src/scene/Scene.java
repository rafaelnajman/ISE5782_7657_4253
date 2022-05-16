package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class
 */
public class Scene {
    /**
     * Scene name
     */
    public String name;
    /**
     * Scene background color
     */
    public Color background = Color.BLACK;
    /**
     * Scene ambient light
     */
    public AmbientLight ambientLight = new AmbientLight();
    /**
     * Scene geometries
     */
    public Geometries geometries = new Geometries();
    /**
     * Scene light sources
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * constructor for scene
     * @param name name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * add background color to the scene
     * @param background background color
     * @return scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * add ambient light to the scene
     * @param ambientLight ambient light
     * @return scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * add geometries to the scene
     * @param geometries geometries to add
     * @return scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * add light to the scene
     * @param light light to add
     * @return scene
     */
    public Scene addLight(LightSource light) {
        lights.add(light);
        return this;
    }

}
