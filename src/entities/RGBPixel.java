package entities;

public class RGBPixel implements Pixel {

    Position position;
    int red;
    int green;
    int blue;

    public RGBPixel(){}

    public RGBPixel(Position position, int rgb){
        this.red = rgb >> 16&0x000000F;
        this.green = rgb >> 8&0x000000F;
        this.blue = rgb >> 0&0x000000F ;
        this.position = position;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
    @Override
    public Position getPosition() {
        return position;
    }
}
