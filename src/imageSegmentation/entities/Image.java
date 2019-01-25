package imageSegmentation.entities;

import imageSegmentation.entities.pixel.Pixel;
import imageSegmentation.entities.pixel.RGBPixel;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class Image {


    BufferedImage image = null;
    String filename;

    public Image(String filename){
        try{
            this.image = ImageIO.read(new File(filename));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Image(BufferedImage image, String filename){
        this.image = image;
        this.filename = filename;
    }
    // Constructor should load image

    //TODO Prepare saveImage method
    public void saveImage(){
        File file = new File(this.filename);

        try{
            ImageIO.write(this.image,"png",file);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Pixel getPixel(Position position){
        if (this.image != null){
            return new RGBPixel(position, new RGB(this.image.getRGB(position.getX(), position.getY())));
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
