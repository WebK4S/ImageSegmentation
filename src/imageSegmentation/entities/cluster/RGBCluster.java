package imageSegmentation.entities.cluster;

import imageSegmentation.entities.RGB;
import imageSegmentation.entities.pixel.Pixel;
import imageSegmentation.entities.pixel.RGBPixel;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static java.lang.Math.abs;

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

    public int calcDistance(Pixel pixel, Pixel center){
        int redDifference = abs(pixel.getRgb().getRed() -  center.getRgb().getRed());
        int blueDifference = abs(pixel.getRgb().getBlue() -  center.getRgb().getBlue());
        int greenDifference = abs(pixel.getRgb().getGreen() -  center.getRgb().getGreen());

        return (redDifference + blueDifference + greenDifference)/3;
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
        RGB meanColor = calcMeanColor();

        if (meanColor != null){
            for (RGBPixel pixel : pixelSet){
                pixel.setRgb(meanColor);
            }
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

        Pixel centerCandidate = null;

        for (RGBPixel tempCenter : pixelSet){
            int minValue = Integer.MAX_VALUE;
            int sum = 0;
            for (RGBPixel pixel : pixelSet){
                sum += calcDistance(pixel, tempCenter);
            }
            //System.out.println("Sum: " + sum);
            if (sum<minValue){
                //System.out.println("Sum         : " + sum);
                centerCandidate = tempCenter;

            }
        }
        System.out.println("Updated center:" + centerCandidate.toString());
        if(!center.getRgb().equals(centerCandidate.getRgb())){
            System.out.println("In UpdateCenter");
            updateCenter(centerCandidate);
        }
    }

    public void addPixel(Pixel pixel){
        reds += pixel.getRgb().getRed();
        greens += pixel.getRgb().getGreen();
        blues += pixel.getRgb().getBlue();
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
        this.center=newCenter;
    }

    public Pixel getCenter() {
        return center;
    }

    public Set getPixelSet() {
        return null;
    }

}
