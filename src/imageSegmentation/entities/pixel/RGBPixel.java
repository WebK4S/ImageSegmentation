package imageSegmentation.entities.pixel;

import imageSegmentation.entities.Position;
import imageSegmentation.entities.RGB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RGBPixel implements Pixel {


    private Position position;
    private RGB rgb;
    private int clusterId;


    public RGBPixel(Position position, RGB rgb){
        this.rgb = rgb;
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public RGB getRgb() {
        return rgb;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }
}