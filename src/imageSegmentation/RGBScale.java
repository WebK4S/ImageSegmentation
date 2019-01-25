package imageSegmentation;

public class RGBScale implements ColorScale<RGBScale> {

    private int red;
    private int green;
    private int blue;

    @Override
    public double distanceFrom(RGBScale otherColor) {
        return Math.sqrt(Math.pow(this.red - otherColor.red, 2) + Math.pow(this.green - otherColor.green, 2) + Math.pow(this.blue - otherColor.blue, 2));
    }
}
