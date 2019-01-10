package entities;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class Cluster {

    private Set<Pixel> pixelSet;

    private Pixel center;

    public void clear(){}
    public void addPixel(Pixel pixel){
        pixelSet.add(pixel);
    };
    public void removePixel(Pixel pixel){
        pixelSet.remove(pixel);
    };

    public void updateCenter(Pixel newCenter){
        center=newCenter;
    };


    public void setCenter(Pixel center) {
        this.center = center;
    }

    public Pixel getCenter() {
        return center;
    }

    public Set getPixels(){
        return pixelSet;
    }

}
