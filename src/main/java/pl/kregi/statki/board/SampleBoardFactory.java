package pl.kregi.statki.board;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class SampleBoardFactory {
    public Board createBoard(){
        Board board1 = new Board(10);
        Board board2 = new Board(10);
        Point startPoint = new Point(4, 1);
        Point startPoint1 = new Point(9, 1);
        Point startPoint2 = new Point(2, 3);
        Point startPoint3 = new Point(5, 3);
        Point startPoint4 = new Point(1, 5);
        Point startPoint5 = new Point(7, 6);
        Point startPoint6 = new Point(1, 7);
        Point startPoint7 = new Point(3, 7);
        Point startPoint8 = new Point(9, 8);
        Point startPoint9 = new Point(4, 10);
        Ship ship = Ship.create(startPoint,1,Orientation.HORIZONTAL);
        Ship ship1 = Ship.create(startPoint1, 4, Orientation.VERTICAL);
        Ship ship2 = Ship.create(startPoint2, 1, Orientation.HORIZONTAL);
        Ship ship3 = Ship.create(startPoint3, 3, Orientation.VERTICAL);
        Ship ship4 = Ship.create(startPoint4, 2, Orientation.HORIZONTAL);
        Ship ship5 = Ship.create(startPoint5, 2, Orientation.HORIZONTAL);
        Ship ship6 = Ship.create(startPoint6, 2,Orientation.VERTICAL);
        Ship ship7 = Ship.create(startPoint7, 3, Orientation.HORIZONTAL);
        Ship ship8 = Ship.create(startPoint8, 1, Orientation.HORIZONTAL);
        Ship ship9 = Ship.create(startPoint9, 1, Orientation.HORIZONTAL);
        Map<UUID, Board> boards;
        board1.add(ship);
        board1.add(ship1);
        board1.add(ship2);
        board1.add(ship3);
        board1.add(ship4);
        board1.add(ship5);
        board1.add(ship6);
        board1.add(ship7);
        board1.add(ship8);
        board1.add(ship9);
    }
}
