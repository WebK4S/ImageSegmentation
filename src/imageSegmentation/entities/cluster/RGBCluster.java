package imageSegmentation.entities.cluster;

import imageSegmentation.entities.RGB;
import imageSegmentation.entities.pixel.Pixel;
import imageSegmentation.entities.pixel.RGBPixel;
import imageSegmentation.metrics.EuclidessMetric;
import imageSegmentation.metrics.Metric;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.*;

public class RGBCluster implements Cluster {

    private int reds = 0;
    private int greens = 0;
    private int blues = 0;

    private Pixel center;
    //private int pixelCount;
    private Set<RGBPixel> pixelSet = new HashSet<>();



    public RGBCluster(Pixel center) {
        this.center = center;
        pixelSet.add((RGBPixel)center);

    }

    public double calcDistance(Pixel pixel, Pixel center){
        double redDifference = pow((pixel.getRgb().getRed() -  center.getRgb().getRed()),2);
        double blueDifference = pow((pixel.getRgb().getBlue() -  center.getRgb().getBlue()),2);
        double greenDifference = pow((pixel.getRgb().getGreen() -  center.getRgb().getGreen()),2);
        Metric metric = new EuclidessMetric();
        double distance = metric.calculateDistance(center, pixel);
        return redDifference + blueDifference + greenDifference + distance;
    }

    private RGB calcMeanColor(){
        RGB meanColor = null;

        if (pixelSet.size() != 0){
            int red = reds/pixelSet.size();
            int green = greens/pixelSet.size();
            int blue = blues/pixelSet.size();
            meanColor = new RGB(red, green, blue);
        }
        else{
            System.out.println("Mean color is null");
        }
        return meanColor;
    }

    public void applyColor(){
        /*Random rand = new Random();
        int color = rand.nextInt(255);*/
        RGB meanColor = center.getRgb(); //new RGB(color,color,color);

        if (meanColor != null){
            for (RGBPixel pixel : pixelSet){
                pixel.setRgb(meanColor);
            }
            System.out.println("Color applied: "+ meanColor.toString());
        }
        else {
            System.out.println("Mean color is null, cannot be applied.");
        }
    }

    public void clear(){
        this.reds = 0;
        this.blues = 0;
        this.greens = 0;
        this.center = null;
        //this.pixelCount = 0;
        this.pixelSet.clear();
    }

    @Override
    public void evaluateCenter() {
        double minValue = Integer.MAX_VALUE;

        Pixel centerCandidate = null;
        for (RGBPixel tempCenter : pixelSet){

            double sum = 0;
            for (RGBPixel pixel : pixelSet){
                sum += calcDistance(pixel, tempCenter);
            }
            //System.out.println("Sum: " + sum);
            if (sum<minValue){
                //System.out.println("Sum         : " + sum);
                minValue = sum;
                centerCandidate = tempCenter;

            }
        }
        System.out.println("Old center: "+ center.toString());
        System.out.println("Updated center:" + centerCandidate.toString());
        updateCenter(centerCandidate);
    }

    public void addPixel(Pixel pixel){
        reds += pixel.getRgb().getRed();
        greens += pixel.getRgb().getGreen();
        blues += pixel.getRgb().getBlue();
        pixel.setClusterId(center.getClusterId());
        pixelSet.add((RGBPixel)pixel);
        //pixelCount++;
    }
    public void removePixel(Pixel pixel){
        reds -= pixel.getRgb().getRed();
        greens -= pixel.getRgb().getGreen();
        blues -= pixel.getRgb().getBlue();
        pixelSet.remove(pixel);
        //pixelCount--;
    }


    public void updateCenter(Pixel newCenter){
        newCenter.setClusterId(center.getClusterId());
        this.center=newCenter;
    }

    public Pixel getCenter() {
        return center;
    }

    public Set getPixelSet() {
        return null;
    }

}
