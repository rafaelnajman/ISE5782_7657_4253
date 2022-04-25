package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

/**
 * Tests for ImageWriter class
 */
public class ImageWriterTest {
    @Test

    /**
     * basic tests for ImageWriter class
     */
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        for (int i = 0; i < 800; i++)
            for (int j = 0; j < 500; j++) {
                if ((i % 50 == 0) || (j % 50 == 0))
                    imageWriter.writePixel(i, j, new Color(0, 0, 0));
                else imageWriter.writePixel(i, j, new Color(105, 178, 255));

            }

        imageWriter.writeToImage();
    }
}
