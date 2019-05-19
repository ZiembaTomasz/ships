package pl.kregi.statki.board;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Ship {
    private final Set<Point> positions;
    private final Set<Point> positionsHit;

    private Ship(Set<Point> positions) {
        this.positions = positions;
        this.positionsHit = new HashSet<>();
    }

    public static Ship create(final Point start, final int size, final Orientation orientation) {
        final Set<Point> positions = new HashSet<>();
        positions.add(start);
        Point current = start;
        for (int i = 1; i < size; i++) {
            current = Point.next(current, orientation);
            positions.add(current);
        }
        return new Ship(positions);
    }

}
