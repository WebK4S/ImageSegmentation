package entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {


    BufferedImage image = null;

    public Image(String filename){
        try{
            this.image = ImageIO.read(new File(filename));
        }
        catch (Exception e){
            System.out.println("File not found " + e.getStackTrace());
        }

    }
    // Constructor should load image

    //TODO Prepare saveImage method
    public void saveImage(BufferedImage image, String targetImage){
        File file = new File(targetImage);

        try{
            ImageIO.write(image,"png",file);
        }
        catch (IOException e){
            System.out.println("Unable to save an image "+ e.getMessage());
        }
    };

    public RGBPixel getRGBPixel(Position position){
        if (this.image != null){
            return new RGBPixel(position, this.image.getRGB(position.getX(), position.getY()));
        }
        return null;
    }

    public int getImageWidth(){
        return image.getWidth();
    }

    public int getImageHeight(){
        return image.getHeight();
    }



}
