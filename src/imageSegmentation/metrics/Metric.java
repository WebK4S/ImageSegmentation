package imageSegmentation.metrics;

import imageSegmentation.entities.pixel.Pixel;

public interface Metric {

    public double calculateDistance(Pixel center, Pixel pixel);

}
