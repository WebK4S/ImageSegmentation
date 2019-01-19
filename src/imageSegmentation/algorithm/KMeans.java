package imageSegmentation.algorithm;

import imageSegmentation.entities.Cluster;
import imageSegmentation.entities.Image;
import imageSegmentation.entities.Pixel;
import imageSegmentation.entities.Position;

import java.util.HashMap;
import java.util.Map;

public class KMeans {


    private boolean changedCluster = true;

    private Image image;
    private Integer numberOfClusters;
    private Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();

    public KMeans(Image image, Integer numberOfClusters){
        this.image = image;
        this.numberOfClusters = numberOfClusters;
    }

    public Cluster findClosestCluster(Pixel pixel){
        int distance = Integer.MAX_VALUE;
        int size = clusters.size();
        for (int i = 0; i < size; i++){
            Cluster cluster = clusters.get(i);
            int distanceToCluster = cluster.calcDistance(pixel);
            if(distanceToCluster < distance){
                distance = distanceToCluster;
                if (pixel.getClusterId() != i){
                    return cluster;
                }
            }

        }
        return null;
    }

    public void initClusters(){
        int width = image.getImageWidth();
        int height = image.getImageHeight();

        int x = 0;
        int y = 0;

        int dx = width/numberOfClusters;
        int dy = height/numberOfClusters;

        for (Integer i = 0; i<numberOfClusters; i++){
            createCluster(i, image.getRGBPixel(new Position(x,y)));
            x+=dx;
            y+=dy;
        }
    }

    public void createCluster (Integer id, Pixel center ){
        Cluster cluster = new Cluster(center);
        clusters.put(id, cluster);
    }

    public Cluster getCluster(Integer id){
        return this.clusters.get(id);
    }
}
