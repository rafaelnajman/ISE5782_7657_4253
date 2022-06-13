package unittests.renderer;

import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Class to render final image
 */
public class FinalImage {

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(200, 200).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene));

    @Test
    public void renderFinalImage() {
        createRoom();
        createLights();
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
        camera.setImageWriter(new ImageWriter("FinalImage", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }

    public void createRoom() {
        scene.geometries.add( //
                //left wall
                new Polygon(new Point(-70, 70, 300), new Point(-70, -70, 300), new Point(-70, -70, -400), new Point(-70, 70, -400)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                //ceiling
                new Polygon(new Point(70, 70, 300), new Point(-70, 70, 300), new Point(-70, 70, -400), new Point(70, 70, -400)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                //right wall
                new Polygon(new Point(70, -70, 300), new Point(70, 70, 300), new Point(70, 70, -400), new Point(70, -70, -400)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                // floor
                new Polygon(new Point(-70, -70, 300), new Point(70, -70, 300), new Point(70, -70, -400), new Point(-70, -70, -400)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                // back wall
                new Polygon(new Point(70, 70, -400), new Point(-70, 70, -400), new Point(-70, -70, -400), new Point(70, -70, -400)).setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                new Sphere(new Point(0,0,0), 50).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100))
        );
    }

    public void createLights(){
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point(0, -20, 70), new Vector(0, -1, 0)));
    }
}