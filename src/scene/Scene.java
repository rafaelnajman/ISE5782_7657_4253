package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.*;

public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    public Scene(String name){
        this.name = name;
        geometries = new Geometries();
    }
    public Scene setBackground(Color background){
        this.background = background;
        return this;
    }
    public Scene setAmbientLight(AmbientLight ambientLight){
        this.ambientLight = ambientLight;
        return this;
    }
    public Scene setGeometries(Geometries geometries){
        this.geometries = geometries;
        return this;
    }

}
