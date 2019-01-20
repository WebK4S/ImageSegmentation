package imageSegmentation.algorithm;

import imageSegmentation.entities.Image;
import imageSegmentation.entities.Position;
import imageSegmentation.entities.cluster.Cluster;
import imageSegmentation.entities.cluster.GSCluster;
import imageSegmentation.entities.cluster.RGBCluster;
import imageSegmentation.entities.pixel.Pixel;

import java.util.HashMap;
import java.util.Map;

public class KMeans {


    private boolean changedCluster = true;

    private Image image;
    private Integer numberOfClusters;
    private Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();
    private ColorType colorType;

    public KMeans(Image image, Integer numberOfClusters, ColorType colorType){
        this.image = image;
        this.numberOfClusters = numberOfClusters;
        this.colorType = colorType;
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

        Cluster cluster = null;

        switch (colorType){
            case GS:
                cluster = new GSCluster(center);
                break;

            case RGB:
                cluster = new RGBCluster(center);
                break;

             default:
                 System.out.println("Unsupported color type");
                 return;
        }
        clusters.put(id, cluster);
    }

    public Cluster getCluster(Integer id){
        return this.clusters.get(id);
    }
}
