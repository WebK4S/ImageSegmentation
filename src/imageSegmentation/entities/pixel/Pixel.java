package imageSegmentation.entities.pixel;

import imageSegmentation.entities.Position;
import imageSegmentation.entities.RGB;

public interface Pixel {

    public RGB getRgb();
    public Position getPosition();
    public int getClusterId();

}
