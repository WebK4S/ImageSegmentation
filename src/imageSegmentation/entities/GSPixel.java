package imageSegmentation.entities;

import org.springframework.stereotype.Component;

//Grey scale pixel
//I = 0.2989 *R + 0.5870 * G + 0.1140 * B
@Component
public class GSPixel implements Pixel{

    int brightness;


    public int evaluate(Pixel pixel) {
        return 0;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public Position getPosition() {
        return null;
    }
}
