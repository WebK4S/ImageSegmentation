package segmentation;

import entities.Cluster;
import entities.Image;

import java.awt.image.BufferedImage;
import java.util.Set;

public class Segmentation {

    Image image;

    public Cluster createCluster(){};
    public Set<Cluster> createClusters(BufferedImage image, int nummberOfClusters){}
}
