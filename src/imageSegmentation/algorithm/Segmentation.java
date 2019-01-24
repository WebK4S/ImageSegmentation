package imageSegmentation.algorithm;

import imageSegmentation.entities.Image;

import java.awt.image.BufferedImage;

public class Segmentation {

    public static final int NUMBER_OF_CLUSTERS = 32;
    public static final String FILENAME = "test3.png";
    public static final String RESULT_FILE = "result.png";
    public static final String SRC = System.getProperty("user.dir") + "\\res\\";


    public static void main(String args[]){

        Image image = new Image(SRC +FILENAME);
        KMeans kMeans = new KMeans(image, NUMBER_OF_CLUSTERS, ColorType.RGB);
        BufferedImage newImage = kMeans.run();

        Image resultImage = new Image(newImage, SRC + RESULT_FILE);
        resultImage.saveImage();

    }


    //TODO Implement create method

}
