package imageSegmentation.entities.cluster;

import imageSegmentation.entities.pixel.Pixel;

import java.util.Set;

public interface Cluster {

    public double calcDistance(Pixel pixel, Pixel center);
    public void addPixel(Pixel pixel);
    public void removePixel(Pixel pixel);
    public void updateCenter(Pixel pixel);
    public Pixel getCenter();
    public Set getPixelSet();
    public void clear();
    public void evaluateCenter();
    public void applyColor();
}
