package pl.kregi.statki.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

@EqualsAndHashCode
@Getter
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
}
