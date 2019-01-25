package imageSegmentation;

import imageSegmentation.entities.Position;

public class Pixel<SCALE extends ColorScale> {

    private SCALE value;
    private Position position;


    public double distanceFrom(Pixel<SCALE> otherPixel) {
        return Math.sqrt(Math.pow(this.value.distanceFrom(otherPixel.value), 2) + Math.pow(this.position.distanceFrom(otherPixel.position), 2));
    }
}
