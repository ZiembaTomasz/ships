package pl.kregi.statki.board;

import lombok.Getter;

import java.util.*;

@Getter
public class Board {

    private final int size;
    private final Map<Point, Ship> occupied;

    public Board(final int size) {
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
    public Ship shot(Point point){

        if(this.hasOnPosition(point)){
            Ship ship = occupied.get(point);
            ship.hitShip(point);
            return ship;
        }
        return null;
    }
    public int counterPoints(){
        Collection<Ship> myShips = occupied.values();
        int counter = 0;
        for (Ship ships: myShips){
            if (ships.isSunk()){
                counter++;
            }
        }
        return counter;
    }
    public boolean allSunk() {
        int ships = getOccupied().size();
        int shipsCounter = 0;
        for (Ship ship : occupied.values()) {
            if (ship.isSunk()) {
                shipsCounter++;
            }
        }
        if (ships == shipsCounter) {
            return true;
        }
        return false;
    }
}
