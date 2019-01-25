package imageSegmentation.algorithm;

import imageSegmentation.entities.Image;
import imageSegmentation.entities.Position;
import imageSegmentation.entities.cluster.Cluster;
import imageSegmentation.entities.cluster.GSCluster;
import imageSegmentation.entities.cluster.RGBCluster;
import imageSegmentation.entities.pixel.Pixel;
import imageSegmentation.metrics.EuclidessMetric;
import imageSegmentation.metrics.Metric;

import java.awt.image.BufferedImage;
import java.util.*;

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

    public KMeans(Image image, Integer numberOfClusters, ColorType colorType) {
        this.image = image;
        this.numberOfClusters = numberOfClusters;
        this.colorType = colorType;
    }

    public BufferedImage run() {
        convertImageToPixels();
        initClusters();
        System.out.println(pixelSet.size());
        System.out.println(clusters.size());
        while (pixelChanged) {
            pixelChanged = FALSE;
            for (Pixel pixel : pixelSet) {
                if (isCenter(pixel)){
                    continue;
                }
                Cluster cluster = findClosestCluster(pixel);
                if (cluster != null && pixel.getClusterId() != cluster.getCenter().getClusterId()) {
                    pixelChanged = TRUE;
                    if (pixel.getClusterId() != null && !cluster.getCenter().equals(pixel)) {
                        clusters.get(pixel.getClusterId()).removePixel(pixel);
                    }
                    cluster.addPixel(pixel);
                }
            }
            System.out.println("Seggregation has been made.");
            for (Cluster cluster : clusters.values()) {
                cluster.evaluateCenter();
            }
            System.out.println("Centers evaluated.");
            System.out.println(pixelChanged);
        }
        BufferedImage newImage = new BufferedImage(image.getImageWidth(), image.getImageHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (Cluster cluster : clusters.values()) {
            cluster.applyColor();
        }
        for (Pixel pixel : pixelSet) {
            newImage.setRGB(pixel.getPosition().getX(), pixel.getPosition().getY(),
                    pixel.getRgb().comvertToInt());
        }
        return newImage;
    }

    public boolean isCenter(Pixel pixel){
        for (Cluster cluster : clusters.values()){
            if(cluster.getCenter().equals(pixel)){
                return TRUE;
            }
        }
        return FALSE;
    }

    public Cluster findClosestCluster(Pixel pixel) {
        double distance = Integer.MAX_VALUE;
        int size = clusters.size();
        Cluster closestCluster = null;
        for (int i = 0; i < size; i++) {
            Cluster cluster = clusters.get(i);
            double distanceToCluster = cluster.calcDistance(pixel, cluster.getCenter());
            if (distanceToCluster < distance) {
                distance = distanceToCluster;
                closestCluster = cluster;
            }
        }
        return closestCluster;

    }

    public void initClusters() {
        int width = image.getImageWidth();
        int height = image.getImageHeight();
        Random rand = new Random();
        Metric metric = new EuclidessMetric();

        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        createCluster(0, image.getPixel(new Position(x, y)));
        double max = 0;
        Pixel centerCandidate;
        for (Integer i = 1; i < numberOfClusters; i++) {
            centerCandidate = null;
            max = 0;
            for (Pixel pixel : pixelSet){
                if (isCenter(pixel)){
                    continue;
                }
                double sum =0;
                for (Cluster cluster : clusters.values()){
                    sum += metric.calculateDistance(cluster.getCenter(), pixel);
                }

                if (sum > max){
                    max = sum;
                    centerCandidate = pixel;
                }
            }
            createCluster(i, centerCandidate);
        }


        /*int dx = width / numberOfClusters;
        int dy = height / numberOfClusters;

        for (Integer i = 0; i < numberOfClusters; i++) {
            createCluster(i, image.getPixel(new Position(x, y)));
            x += dx;
            y += dy;
        }*/
    }

    private void createCluster(Integer id, Pixel center) {

        Cluster cluster = null;

        switch (colorType) {
            case GRAYSCALE:
                center.setClusterId(id);
                cluster = new GSCluster(center);
                break;

            case RGB:
                center.setClusterId(id);
                cluster = new RGBCluster(center);
                break;

            default:
                System.out.println("Unsupported color type");
                return;
        }
        clusters.put(id, cluster);
    }

    private void convertImageToPixels() {
        Random rand = new Random();
        for (int i = 0; i < image.getImageWidth(); i++) {
            for (int j = 0; j < image.getImageHeight(); j++) {
                Pixel pixel = image.getPixel(new Position(i, j));
                //pixel.setClusterId(rand.nextInt(NUMBER_OF_CLUSTERS));
                pixelSet.add(pixel);
            }
        }
        System.out.println("Image converted.");
    }

    public Cluster getCluster(Integer id) {
        return this.clusters.get(id);
    }
}
