package imageSegmentation.metrics;

import imageSegmentation.entities.pixel.Pixel;
import org.springframework.stereotype.Component;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

@Component
public class EuclidessMetric implements Metric {

    @Override
    public double calculateDistance(Pixel center, Pixel pixel) {
        double distance;

        distance = abs((center.getPosition().getX() - pixel.getPosition().getX())^2 +
                (center.getPosition().getY() - pixel.getPosition().getY())^2);

        return distance;
    }

}
