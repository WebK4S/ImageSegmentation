package imageSegmentation.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClusterTest {

    @BeforeEach
    void setUp() {
        Pixel pixel = new RGBPixel(new Position(0,0), new RGB(256));
        Cluster cluster = new Cluster(pixel);
    }

    @Test
    void calcDistance() {
    }

    @Test
    void addPixel() {
    }

    @Test
    void removePixel() {
    }
}