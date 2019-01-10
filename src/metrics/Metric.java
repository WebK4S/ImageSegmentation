package metrics;

import entities.Pixel;

public interface Metric {

    public int calculateDistance(Pixel center, Pixel pixel);

}
