package imageSegmentation.entities.cluster;

import imageSegmentation.entities.pixel.Pixel;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class RGBCluster implements Cluster {

    private int reds = 0;
    private int greens = 0;
    private int blues = 0;

    private Pixel center;
    private int pixelCount;
    private Set<Pixel> pixelSet = new HashSet<>();



    public RGBCluster(Pixel center) {
        this.center = center;

    }

    public int calcDistance(Pixel pixel){
        int redDifference = abs(pixel.getRgb().getRed() -  center.getRgb().getRed());
        int blueDifference = abs(pixel.getRgb().getBlue() -  center.getRgb().getBlue());
        int greenDifference = abs(pixel.getRgb().getGreen() -  center.getRgb().getGreen());

        return (redDifference + blueDifference + greenDifference)/3;
    }

    public void clear(){
        this.reds = 0;
        this.blues = 0;
        this.greens = 0;
        this.center = null;
        this.pixelCount = 0;
        this.pixelSet.clear();
    }
    public void addPixel(Pixel pixel){
        reds += pixel.getRgb().getRed();
        greens += pixel.getRgb().getGreen();
        blues += pixel.getRgb().getBlue();
        pixelSet.add(pixel);
        pixelCount++;
    }
    public void removePixel(Pixel pixel){
        reds -= pixel.getRgb().getRed();
        greens -= pixel.getRgb().getGreen();
        blues -= pixel.getRgb().getBlue();
        pixelSet.remove(pixel);
        pixelCount--;
    }

    public void updateCenter(Pixel newCenter){
        this.center=newCenter;
    }

    public Pixel getCenter() {
        return center;
    }

    public Set getPixelSet() {
        return null;
    }

}
