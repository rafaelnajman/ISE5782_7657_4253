package scene;


import geometries.*;
import lighting.AmbientLight;
import org.xml.sax.SAXException;
import primitives.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

/**
 * This class is responsible for parsing the XML file and creating the scene
 */
public class XML {

    /**
     * function parses XML file
     *
     * @param scene    the scene to add the objects to
     * @param fileName file that contains XML
     * @throws ParserConfigurationException exception
     * @throws IOException                  exception
     * @throws SAXException                 exception
     */
    public static void sceneParser(Scene scene, String fileName) throws ParserConfigurationException, IOException, SAXException {
        //build the parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fileName));
        document.getDocumentElement().normalize();

        var root = document.getDocumentElement();



        scene.setBackground(parseColor(root.getAttribute("background-color")));

        var ambient = (Element) root.getChildNodes().item(1);
        scene.setAmbientLight(new AmbientLight(parseColor(ambient.getAttribute("color")), new Double3(1d, 1d, 1d)));

        var geoLst = root.getChildNodes().item(3).getChildNodes();
        //parse the geometries
        Geometries geometries = new Geometries();
        Point p0;

        for (int i = 0; i < geoLst.getLength(); i++) {
            var geo = geoLst.item(i);
            if (geo instanceof Element) {
                var el = (Element) geo;

                switch (geo.getNodeName()) {
                    case "triangle" -> {
                        p0 = parsePoint(el.getAttribute("p0"));
                        Point p1 = parsePoint(el.getAttribute("p1"));
                        Point p2 = parsePoint(el.getAttribute("p2"));
                        geometries.add(new Triangle(p0, p1, p2));
                    }
                    case "sphere" -> {
                        Point center = parsePoint(el.getAttribute("center"));
                        double radius = Integer.parseInt(el.getAttribute("radius"));
                        geometries.add(new Sphere(center, radius));
                    }
                    case "plane" -> {
                        p0 = parsePoint(el.getAttribute("point"));
                        Vector v = parseVector(el.getAttribute("vector"));
                        geometries.add(new Plane(p0, v));
                    }
                }
            }
            scene.setGeometries(geometries);
        }
    }

    /**
     * This method parses a vector from a string
     *
     * @param toParse the string to parse
     * @return the point parsed
     */
    private static Vector parseVector(String toParse) {
        var parsed = toParse.split(" ");
        return new Vector(Integer.parseInt(parsed[0]), Integer.parseInt(parsed[1]), Integer.parseInt(parsed[2]));
    }

    /**
     * This method parses a point from a string
     *
     * @param toParse the string to parse
     * @return the point parsed
     */
    public static Point parsePoint(String toParse) {
        var parsed = toParse.split(" ");
        return new Point(Integer.parseInt(parsed[0]), Integer.parseInt(parsed[1]), Integer.parseInt(parsed[2]));

    }

    /**
     * This method parses a color from a string
     *
     * @param toParse the string to parse
     * @return the color parsed
     */
    public static Color parseColor(String toParse) {
        var parsed = toParse.split(" ");
        return new Color(Integer.parseInt(parsed[0]), Integer.parseInt(parsed[1]), Integer.parseInt(parsed[2]));

    }
}

