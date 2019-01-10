package entities;

import java.awt.image.BufferedImage;

public class Image {

    BufferedImage image;

    //TODO Prepare Constructor
    public Image(String filename){};
    // Constructor should load image

    //TODO Prepare saveImage method
    public void saveImage(BufferedImage image){};

    public Pixel getRGBPixel(int x, int y){
    }

    public int getImageWidth(){
        return image.getWidth();
    }



}
