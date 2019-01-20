package imageSegmentation.entities.cluster;

import imageSegmentation.entities.pixel.Pixel;

import java.util.Set;

public class GSCluster implements Cluster {

    private Set<Pixel> pixelSet;
    private Pixel center;
    private int pixelCount;




    public GSCluster(Pixel center) {
    }

    @Override
    public int calcDistance(Pixel pixel) {
        return 0;
    }

    public void clear(){
        this.center = null;
        this.pixelCount = 0;
        this.pixelSet.clear();
    }

    public void addPixel(Pixel pixel){
        pixelSet.add(pixel);
        pixelCount++;
    }
    public void removePixel(Pixel pixel){
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
