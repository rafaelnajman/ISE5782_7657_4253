package unittests.CameraTests;

import static org.junit.jupiter.api.Assertions.*;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import renderer.Camera;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Testing Camera Class
 * 
 * @author Dan
 *
 */
class CameraTest {
	static final Point ZERO_POINT = new Point(0, 0, 0);

	/**
	 * Test method for
	 * {@link renderer.Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void testConstructRay() {
		Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
		String badRay = "Bad ray";

		// ============ Equivalence Partitions Tests ==============
		// EP01: 4X4 Inside (1,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)),
			camera.setVPSize(8, 8).constructRay(4, 4, 1, 1), badRay);

		// =============== Boundary Values Tests ==================
		// BV01: 3X3 Center (1,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)),
			camera.setVPSize(6, 6).constructRay(3, 3, 1, 1), badRay);

		// BV02: 3X3 Center of Upper Side (0,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 1, 0), badRay);

		// BV03: 3X3 Center of Left Side (1,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 0, 1), badRay);

		// BV04: 3X3 Corner (0,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)),
				camera.setVPSize(6, 6).constructRay(3, 3, 0, 0), badRay);

		// BV05: 4X4 Corner (0,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)),
				camera.setVPSize(8, 8).constructRay(4, 4, 0, 0), badRay);

		// BV06: 4X4 Side (0,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)),
				camera.setVPSize(8, 8).constructRay(4, 4, 1, 0), badRay);

		//=============== Integration Tests ======================
		//Integration 01: check camera ray intersections with sphere at point (0,0,-3) and radius 1

		camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3,3);
		assertEquals(2,numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -3), 1)), camera),
				"Camera ray intersections with sphere at point (0,0,-3) and radius 1");

		//Integration 02: check camera ray intersections with sphere at point (0,0,-2.5) and radius 2.5
		camera = new Camera(new Point(0,0,0.5), new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(1).setVPSize(3,3);

		assertEquals(18,numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -2.5), 2.5)), camera),
				"Integration 02: check camera ray intersections with sphere at point (0,0,-2.5) and radius 2.5");

		//Integration 03: check camera ray intersections with sphere at point (0,0,-2) and radius 2
		assertEquals(10,numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -2), 2)), camera),
				"Integration 03: check camera ray intersections with sphere at point (0,0,-2) and radius 2");

		//Integration 04: check camera ray intersections with sphere at point (0,0,-1) and radius 5
		assertEquals(9,numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -1), 5)), camera),
				"Integration 04: check camera ray intersections with sphere at point (0,0,-1) and radius 5");

		//Integration 05: check camera ray intersections with sphere at point (0,0, 2) and camera pointing to other directions
		assertEquals(0,numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, 2), 0.5)), camera),
				"Integration 05: check camera ray intersections with sphere at point (0,0, 2) and camera pointing to other directions");

		//Integration 06: check camera ray intersections with perpendicular plane
		assertEquals(9,numberOfIntersections(3, 3, new Geometries(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1))), camera),
				"Integration 06: check camera ray intersections with perpendicular plane");

		//Integration 07: check camera ray intersections with non-perpendicular plane
		assertEquals(9,numberOfIntersections(3, 3, new Geometries(new Plane(new Point(0, 0, 0), new Vector(0, 1, 8))), camera),
				"Integration 07: check camera ray intersections with non-perpendicular plane");

		//Integration 08: check camera ray intersections with plane where lower rays don't intersect
		assertEquals(6,numberOfIntersections(3, 3, new Geometries(new Plane(new Point(0, 0, 0), new Vector(0, 3, 1))), camera),
				"Integration 08: check camera ray intersections with plane where lower rays don't intersect");

		//Integration 09: check camera ray intersections with triangle (1 intersection)
		assertEquals(1,numberOfIntersections(3, 3, new Geometries(new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2))),
						camera), "Integration 09: check camera ray intersections with triangle");

		//Integration 10: check camera ray intersections with triangle (2 intersections)
		assertEquals(2,numberOfIntersections(3, 3, new Geometries(new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2))),
				camera), "Integration 10: check camera ray intersections with triangle (2 intersections)");


	}

	/**
	 * This method checks the number of intersections between a ray and a list of geometries
	 * @param nx the x resolution of the pixel
	 * @param ny the y resolution of the pixel
	 * @param geometries the list of geometries
	 * @param camera the camera
	 * @return the number of intersections
	 */
	private int numberOfIntersections (int nx, int ny, Geometries geometries, Camera camera) {
		int numOfIntersections = 0;
		for (int i = 0; i < nx; i++)
			for (int j = 0; j < ny; j++) {
				Ray ray = camera.constructRay(nx, ny, j, i);
				List<Point> intersections = geometries.findIntersections(ray);
				if (intersections != null)
					numOfIntersections += intersections.size();
			}
		return numOfIntersections;
	}

}
