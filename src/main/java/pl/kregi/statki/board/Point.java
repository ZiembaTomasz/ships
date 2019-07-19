package pl.kregi.statki.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@EqualsAndHashCode
@Getter
@Setter
public class Point {

    private final int x;
    private final int y;

    public Point(final int x, final int y) {
        Assert.isTrue(x >= 0 && y >= 0, "Point cannot have negative values");
        this.x = x;
        this.y = y;
    }

    public static Point next(final Point from, final Orientation direction) {
        return new Point(from.x + direction.getValue().getX(), from.getY() + direction.getValue().getY());
    }

    public static Point add(final Point position, final int x, final int y) {
        return new Point(position.getX() + x, position.getY() + y);
    }
    public String xConverter(){
        if(getX() == 1){
            return "a";
        }
        if(getX()==2){
            return "b";
        }
        if(getX()==3){
            return "b";
        }
        if(getX()==4){
            return "c";
        }
        if(getX()==5){
            return "d";
        }
        if(getX()==6){
            return "e";
        }
        if(getX()==7){
            return "f";
        }
        if(getX()==8){
            return "g";
        }
        if(getX()==9){
            return "h";
        }
        return "zly strzal";
    }
    public String yConverter(){
        return String.valueOf(getY());
    }
    public String concantenationXY(){
        xConverter();
        yConverter();
        String shot = xConverter() + yConverter();
        return shot;
    }
}
