package imageSegmentation;

public class GrayScale implements ColorScale<GrayScale> {

    private int brightness;

    @Override
    public double distanceFrom(GrayScale otherColor) {
        return Math.abs(this.brightness - otherColor.brightness);
    }
}
