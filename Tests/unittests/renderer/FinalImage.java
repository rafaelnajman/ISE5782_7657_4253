package unittests.renderer;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
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
            .setRayTracer(new RayTracerBasic(scene))
            .setUseAdaptive(false)
            .setMaxAdaptiveLevel(3);

    /**
     * Render final image
     */
    @Test
    public void renderFinalImage() {
        createRoom();
        createLights();
        createTable();
        createChess();
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
        camera.setImageWriter(new ImageWriter("FinalImage", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }

    /**
     * render final image with anti-aliasing
     */
    @Test
    public void renderFinalImageWithAntiAliasing() {
        createRoom();
        createLights();
        createTable();
        createChess();
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
        camera.setImageWriter(new ImageWriter("FinalImageAntiAliasing", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setAntiAliasingFactor(9)
                .renderImage() //
                .writeToImage();
    }

    /**
     * render final image with adaptive supersampling
     */
    @Test
    public void renderFinalImageWithAdaptiveSuperSampling() {
        createRoom();
        createLights();
        createTable();
        createChess();
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
        camera.setImageWriter(new ImageWriter("FinalImageAntiAliasingSuperSampling", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setUseAdaptive(true)
                .setMaxAdaptiveLevel(4)
                .renderImage() //
                .writeToImage();
    }



    /**
     * function creates room
     */
    public void createRoom() {
        scene.geometries.add( //
                //left wall
                new Polygon(new Point(-70, 70, 300), new Point(-70, -70, 300), new Point(-70, -70, -400), new Point(-70, 70, -400)).setEmission(new Color(235, 206, 91).reduce(2)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                //ceiling
                new Polygon(new Point(70, 70, 300), new Point(-70, 70, 300), new Point(-70, 70, -400), new Point(70, 70, -400)).setEmission(new Color(223, 209, 163).reduce(3)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                //right wall
                new Polygon(new Point(70, -70, 300), new Point(70, 70, 300), new Point(70, 70, -400), new Point(70, -70, -400)).setEmission(new Color(235, 206, 91).reduce(2)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                // floor
                new Polygon(new Point(-70, -70, 300), new Point(70, -70, 300), new Point(70, -70, -400), new Point(-70, -70, -400)).setEmission(new Color(223, 209, 163).reduce(3)) //
                        .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100)),
                // back wall
                new Polygon(new Point(70, 70, -400), new Point(-70, 70, -400), new Point(-70, -70, -400), new Point(70, -70, -400)).setEmission(new Color(235, 206, 91).reduce(2.5)) //
                        .setMaterial(new Material().setKs(0.3).setKr(0.5).setShininess(100))

        );
    }

    /**
     * function creates lights
     */
    public void createLights() {

        scene.setBackground(new Color(WHITE));

        Point p1 = new Point(-20, 30, 60);
        Point p2 = new Point(20, 30, 300);

        scene.addLight(new SpotLight(new Color(255, 100, 100).reduce(3), p1, new Vector(0, -1, 0.25)));
        scene.addLight(new PointLight(new Color(255, 100, 100).reduce(3), p1));

        scene.addLight(new SpotLight(new Color(255, 100, 100).reduce(3), p2, new Vector(0, -1, -0.25)));
        scene.addLight(new PointLight(new Color(255, 100, 100).reduce(3), p2));

        scene.addLight(new DirectionalLight(new Color(255, 100, 100).reduce(6), new Vector(0, -0.3, -1)));


        scene.geometries.add(new Sphere(p1, 7).setEmission(new Color(255, 100, 100))
                .setMaterial(new Material().setKt(0.8).setShininess(100)));

        scene.geometries.add(new Sphere(p2, 7).setEmission(new Color(255, 100, 100))
                .setMaterial(new Material().setKt(0.8).setShininess(100)));

        scene.geometries.add(new Cylinder(new Ray(p1.add(new Vector(0, 7, 0)), new Vector(0, 1, 0)), 1, 50).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setKd(0.4).setShininess(100)));

        scene.geometries.add(new Cylinder(new Ray(p2.add(new Vector(0, 7, 0)), new Vector(0, 1, 0)), 1, 50).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setKd(0.4).setShininess(100)));


    }

    /**
     * function creates table
     */
    public void createTable() {
        Point A = new Point(-20, -20, 600);
        Point B = new Point(-20, -20, 100);
        Point C = new Point(20, -20, 100);
        Point D = new Point(20, -20, 600);

        Point E = new Point(-20, -23, 600);
        Point F = new Point(-20, -23, 100);
        Point G = new Point(20, -23, 100);
        Point H = new Point(20, -23, 600);

        scene.geometries.add(
                new Polygon(A, B, C, D).setEmission(new Color(75, 61, 4))//
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Polygon(E, F, G, H).setEmission(new Color(75, 61, 4))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Polygon(A, B, F, E).setEmission(new Color(75, 61, 4))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Polygon(C, D, H, G).setEmission(new Color(75, 61, 4))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Polygon(A, E, H, D).setEmission(new Color(75, 61, 4))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Polygon(B, F, G, C).setEmission(new Color(75, 61, 4))
                        .setMaterial(new Material().setKd(0.7).setKs(0.3))

        );

        //add table feet
        scene.geometries.add(
                new Cylinder(new Ray(E.add(new Vector(5, 0, -5)), new Vector(0, -1, 0)), 2, 40)
                        .setEmission(new Color(75, 61, 4)).setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Cylinder(new Ray(F.add(new Vector(5, 0, 10)), new Vector(0, -1, 0)), 2, 100)
                        .setEmission(new Color(75, 61, 4)).setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Cylinder(new Ray(G.add(new Vector(-5, 0, 10)), new Vector(0, -1, 0)), 2, 100)
                        .setEmission(new Color(75, 61, 4)).setMaterial(new Material().setKd(0.7).setKs(0.3)),
                new Cylinder(new Ray(H.add(new Vector(-5, 0, -5)), new Vector(0, -1, 0)), 2, 40)
                        .setEmission(new Color(75, 61, 4)).setMaterial(new Material().setKd(0.7).setKs(0.3))

        );


    }

    /**
     * function creates chess board
     */
    public void createChess() {
        Point A = new Point(-10, -19.9, 500);
        Point B = new Point(-10, -19.9, 300);
        Point C = new Point(10, -19.9, 300);
        Point D = new Point(10, -19.9, 500);

        scene.geometries.add(
                //white part of board
                new Polygon(A, B, C, D).setEmission(new Color(WHITE))//
                        .setMaterial(new Material().setKd(0.7).setKs(0.3))

        );

        //create board squares
        double y = -19.8;
        for (double i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    scene.geometries.add(
                            new Polygon(new Point(i * 2.5 - 10, y, j * 25 + 300),
                                    new Point(i * 2.5 - 10, y, j * 25 + 300 + 25),
                                    new Point(i * 2.5 + 2.5 - 10, y, j * 25 + 300 + 25),
                                    new Point(i * 2.5 + 2.5 - 10, y, j * 25 + 300)
                            ).setEmission(new Color(BLACK))
                                    .setMaterial(new Material().setKd(0.7).setKs(0.3))
                    );
                }

            }
        }

        //create black board pieces
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                scene.geometries.add(
                        new Sphere(new Point(i * 2.5 - 11.25, y, j * 25 + 300 + 13.75), 0.8)
                                .setEmission(new Color(BLACK))
                                .setMaterial(new Material().setKd(0.7).setKs(0.3))
                );

            }
        }

        //create white board pieces
        for (int i = 7; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                scene.geometries.add(
                        new Sphere(new Point(i * 2.5 - 11.25, y, j * 25 + 300 + 13.75), 0.8)
                                .setEmission(new Color(235, 206, 91))
                                .setMaterial(new Material().setKd(0.7).setKs(0.3))
                );

            }
        }


    }
}
