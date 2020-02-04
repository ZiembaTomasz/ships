package pl.ziembatomasz.statki.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private final int size;
    private final Map<Point, Ship> occupied;

    private Board(final int size) {
        this.size = size;
        this.occupied = new HashMap<>();
    }

    public static Board withShips(final Set<Ship> ships, final int size) {
        final Board newBoard = new Board(size);
        for (final Ship ship : ships) {
            newBoard.add(ship);
        }
        return newBoard;
    }

    public void add(final Ship ship) {
        checkBounds(ship.getPositions());
        checkAlreadyOccupied(ship.getPositions());
        checkCollisions(ship);
        for (Point position : ship.getPositions()) {
            occupied.put(position, ship);
        }
    }

    private void checkAlreadyOccupied(Set<Point> positions) {
        for (final Point position : positions) {
            if (hasOnPosition(position)) {
                throw new ShipCollisionException();
            }
        }
    }

    private void checkBounds(final Set<Point> positions) {
        for (final Point position : positions) {
            if (!inBounds(position)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkCollisions(final Ship ship) {
        for (final Point position : ship.getPositions()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (hasOnPosition(Point.add(position, i, j))) {
                        throw new ShipCollisionException();
                    }
                }
            }
        }
    }

    private boolean inBounds(final Point point) {
        return point.getX() > 0 && point.getY() > 0 && point.getX() <= size && point.getY() <= size;

    }

    public boolean hasOnPosition(final Point position) {
        return occupied.containsKey(position);
    }
}
