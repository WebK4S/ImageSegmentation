package imageSegmentation.metrics;

import imageSegmentation.entities.Pixel;

public interface Metric {

    public double calculateDistance(Pixel center, Pixel pixel);

}
