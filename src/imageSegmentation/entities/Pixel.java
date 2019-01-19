package imageSegmentation.entities;

public interface Pixel {

    public RGB getRgb();
    public Position getPosition();
    public int getClusterId();

}
