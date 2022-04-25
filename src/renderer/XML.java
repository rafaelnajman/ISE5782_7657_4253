package renderer;


import geometries.*;
import lighting.AmbientLight;
import org.xml.sax.SAXException;
import primitives.*;
import scene.Scene;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

public class XML {
    public static void sceneParser(Scene scene, String fileName) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fileName));
        document.getDocumentElement().normalize();

        var root = document.getDocumentElement();

        scene.setBackground(parseColor(root.getAttribute("background-color")));

        var ambient = (Element) root.getChildNodes().item(1);
        scene.setAmbientLight(new AmbientLight(parseColor(ambient.getAttribute("color")), new Double3(1d, 1d, 1d)));

        var geoLst = root.getChildNodes().item(3).getChildNodes();

        Geometries geometries = new Geometries();

        for (int i = 0; i < geoLst.getLength(); i++) {
            var geo = geoLst.item(i);
            if (geo.getNodeName() == "triangle") {
                var el = (Element) geo;
                Point p0 = parsePoint(el.getAttribute("p0"));
                Point p1 = parsePoint(el.getAttribute("p1"));
                Point p2 = parsePoint(el.getAttribute("p2"));

                geometries.add(new Triangle(p0, p1, p2));
            }

            if (geo.getNodeName() == "sphere") {
                var el = (Element) geo;
                Point center = parsePoint(el.getAttribute("center"));
                double radius = Integer.parseInt(el.getAttribute("radius"));

                geometries.add(new Sphere(center, radius));
            }

        }

        scene.setGeometries(geometries);


    }

    public static Point parsePoint(String toParse) {
        var parsed = toParse.split(" ");
        return new Point(Integer.parseInt(parsed[0]),
                Integer.parseInt(parsed[1]),
                Integer.parseInt(parsed[2]));

    }

    public static Color parseColor(String toParse) {
        var parsed = toParse.split(" ");
        return new Color(Integer.parseInt(parsed[0]),
                Integer.parseInt(parsed[1]),
                Integer.parseInt(parsed[2]));

    }
}

