package imageSegmentation.algorithm;

import imageSegmentation.entities.Image;
import imageSegmentation.entities.Position;
import imageSegmentation.entities.cluster.Cluster;
import imageSegmentation.entities.cluster.GSCluster;
import imageSegmentation.entities.cluster.RGBCluster;
import imageSegmentation.entities.pixel.Pixel;
import imageSegmentation.entities.pixel.RGBPixel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class KMeans {


    private boolean changedCluster = true;

    private Image image;
    private Integer numberOfClusters;
    private Map<Integer, Cluster> clusters = new HashMap<>();
    private ColorType colorType;
    private Boolean pixelChanged = true;
    private Set<Pixel> pixelSet = new HashSet<>();

    public KMeans(Image image, Integer numberOfClusters, ColorType colorType){
        this.image = image;
        this.numberOfClusters = numberOfClusters;
        this.colorType = colorType;
    }

    private void convertImageToPixels(){
        for (int i = 0; i<image.getImageWidth(); i++){
            for (int j = 0; j<image.getImageHeight(); j++){
                pixelSet.add(image.getPixel(new Position(i,j)));
            }
        }
        System.out.println("Image converted.");
    }

    public BufferedImage run(){
        initClusters();
        convertImageToPixels();
        System.out.println(pixelSet.size());
        System.out.println(clusters.size());

        while (pixelChanged){
            pixelChanged = FALSE;
            System.out.println(pixelChanged);
            for (Pixel pixel : pixelSet){
                Cluster cluster = findClosestCluster(pixel);
                //System.out.println(cluster);
                if(cluster != null){
                    clusters.get(pixel.getClusterId()).removePixel(pixel);
                    cluster.addPixel(pixel);

                }
            }
            System.out.println("Seggregation has been made.");
            /*for (Cluster cluster : clusters.values()){
                cluster.evaluateCenter();
            }*/
            System.out.println("Centers evaluated.");
        }

        BufferedImage newImage = new BufferedImage(image.getImageWidth(), image.getImageHeight(),
                BufferedImage.TYPE_INT_RGB);

        /*for (Cluster cluster : clusters){
            (RGBCluster)cluster.applyColor();
        }*/


        for (Pixel pixel : pixelSet){
            newImage.setRGB(pixel.getPosition().getX(), pixel.getPosition().getY(),
                    pixel.getRgb().comvertToInt());
        }

        return newImage;


    }

    public Cluster findClosestCluster(Pixel pixel){
        int distance = Integer.MAX_VALUE;
        int size = clusters.size();
        for (int i = 0; i < size; i++){
            Cluster cluster = clusters.get(i);
            int distanceToCluster = cluster.calcDistance(pixel, cluster.getCenter());
            if(distanceToCluster < distance){
                distance = distanceToCluster;
                if (pixel.getClusterId() != i){
                    pixelChanged = TRUE;
                    return cluster;
                }
            }

        }
        pixelChanged = FALSE;
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
            createCluster(i, image.getPixel(new Position(x,y)));
            x+=dx;
            y+=dy;
        }
    }

    private void createCluster (Integer id, Pixel center ){

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
