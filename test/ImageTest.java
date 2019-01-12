import entities.Image;
import entities.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImageTest {
    Image image;

    @BeforeEach
    public void getImage(){
        String src = System.getProperty("user.dir");
        Image image = new Image(src+"\\res\\test.png");
        this.image = image;
    }

    @Test
    public void isImageExistTest(){

        Assertions.assertNotNull(this.image);
    }

    @Test
    public void getRGBTest(){
        Assertions.assertNotNull(image.getRGBPixel(new Position(0,0)));
    }


}
