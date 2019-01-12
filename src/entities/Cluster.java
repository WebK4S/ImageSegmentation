package entities;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class Cluster {

    private Set<Pixel> pixelSet = new HashSet<Pixel>();
    private RGBPixel center;
    private int reds;
    private int greens;
    private int blues;
    private int pixelCount;

    public Cluster(RGBPixel center) {
        this.center = center;
    }

    public int calcDistance(RGBPixel pixel){
        int redDifference = abs(pixel.getRed() -  center.getRed());
        int blueDifference = abs(pixel.getBlue() -  center.getBlue());
        int greenDifference = abs(pixel.getGreen() -  center.getGreen());

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
        pixelSet.add(pixel);
    };
    public void removePixel(Pixel pixel){
        pixelSet.remove(pixel);
    };

    public void updateCenter(RGBPixel newCenter){
        this.center=newCenter;
    };

    public void setCenter(RGBPixel center) {
        this.center = center;
    }
    public Pixel getCenter() {
        return center;
    }
    public Set<Pixel> getPixels(){
        return pixelSet;
    }

}
