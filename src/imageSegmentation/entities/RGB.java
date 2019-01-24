package imageSegmentation.entities;

import org.springframework.stereotype.Component;

@Component
public class RGB {

    private int red;
    private int green;
    private int blue;


    public RGB(int rgb){
        this.red = rgb>>16&0x000000FF;
        this.green = rgb>>8&0x000000FF;
        this.blue = rgb>>0&0x000000FF;
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "RGB{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    public int comvertToInt(){
        return (red << 16 | green << 8 | blue);
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

}
