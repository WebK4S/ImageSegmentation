package entities;

//Grey scale pixel
//I = 0.2989 *R + 0.5870 * G + 0.1140 * B
public class GSPixel extends Pixel{

    int brightness;

    @Override
    public int evaluate(Pixel pixel) {
        return 0;
    }
}
