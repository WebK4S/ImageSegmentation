package algorithm;

import entities.Cluster;
import entities.Image;
import entities.RGBPixel;

import java.util.HashMap;
import java.util.Map;

public class KMeans {


    //May be included into segmentation class
    public static final int NUMBER_OF_CLUSTERS = 10;
    public static final String FILENAME = "test.png";

    public KMeans(Image image){

    }

    private Image image;
    private Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();

    public void createCluster (Integer id, RGBPixel center ){
        Cluster cluster = new Cluster(center);
        clusters.put(id, cluster);
    }
}
