package imageSegmentation;

public interface ColorScale<SCALE extends ColorScale> {

    double distanceFrom(SCALE otherColor);
}
