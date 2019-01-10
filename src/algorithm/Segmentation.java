package algorithm;

import entities.Cluster;
import entities.Pixel;

import java.util.Set;

public abstract class Segmentation {

    private Set<Cluster> clusters;
    private KMeans kMeans;
    private Image image;


    //TODO Implement create method
    public <P extends Pixel> Cluster createCluster (P center ){
        return null;
    }
}
