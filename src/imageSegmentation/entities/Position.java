package imageSegmentation.entities;

import org.springframework.stereotype.Component;

@Component
public class Position {

    private int x;
    private int y;

    public Position(){}

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public double distanceFrom(Position otherPosition) {
        return Math.sqrt(Math.pow(this.x - otherPosition.x, 2) + Math.pow(this.y - otherPosition.y, 2));
    }

}
