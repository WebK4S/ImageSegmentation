package imageSegmentation.entities.pixel;

import imageSegmentation.entities.Position;
import imageSegmentation.entities.RGB;
import org.springframework.stereotype.Component;

//Grey scale pixel
//I = 0.2989 *R + 0.5870 * G + 0.1140 * B
@Component
public class GSPixel implements Pixel{

    private Position position;
    private double brightness;

    public GSPixel(Position position, RGB rgb){
        this.position = position;
        this.brightness = convertRGBtoGS(rgb);
    }

    public double convertRGBtoGS(RGB rgb){
        return rgb.getRed() * 0.2989 + rgb.getGreen() * 0.5870 + rgb.getBlue() * 0.1140;
    }

    public RGB getRgb() {
        return null;
    }
    public Position getPosition() {
        return this.position;
    }
    public Integer getClusterId() {
        return 0;
    }

    @Override
    public void setClusterId(Integer id) {

    }

    public double getBrightness(){
        return brightness;
    }
}
