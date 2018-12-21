package algorithm;

import entities.Center;
import entities.Cluster;
import entities.Pixel;

import java.util.Set;

public abstract class Segmentation {

    private Set<Cluster> clusters;

    //TODO Implement create method
    public <P extends Pixel> Cluster createCluster (int id, P pixel ){
        return null;
    };
}
