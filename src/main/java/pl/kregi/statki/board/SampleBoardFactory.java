package pl.kregi.statki.board;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.kregi.statki.game.Player;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class SampleBoardFactory {
    public Board createFirstBoard(){
        Board board1 = new Board(10);
        //Creating board for first player
        board1.add(Ship.create(new Point(4,1),1,Orientation.HORIZONTAL));
        board1.add(Ship.create(new Point(9,1),4, Orientation.VERTICAL));
        board1.add(Ship.create(new Point(2,3), 1,Orientation.HORIZONTAL));
        board1.add(Ship.create(new Point(5,3), 3, Orientation.VERTICAL));
        board1.add(Ship.create(new Point(1, 5), 2,Orientation.HORIZONTAL));
        board1.add(Ship.create(new Point(7,6), 2, Orientation.HORIZONTAL));
        board1.add(Ship.create(new Point(1, 7), 2, Orientation.VERTICAL));
        board1.add(Ship.create(new Point(3,7), 3, Orientation.HORIZONTAL));
        board1.add(Ship.create(new Point(9,8), 1, Orientation.HORIZONTAL));
        board1.add(Ship.create(new Point(4,10), 1, Orientation.HORIZONTAL));
        return board1;
    }
    public Board createSecondBoard(){
        Board board2 = new Board(10);
        board2.add(Ship.create(new Point(3, 1), 2, Orientation.VERTICAL));
        board2.add(Ship.create(new Point(1, 3), 1, Orientation.HORIZONTAL));
        board2.add(Ship.create(new Point(9, 2), 3, Orientation.VERTICAL));
        board2.add(Ship.create(new Point(3, 5), 4, Orientation.HORIZONTAL));
        board2.add(Ship.create(new Point(8, 6), 2, Orientation.VERTICAL));
        board2.add(Ship.create(new Point(10, 7), 2, Orientation.VERTICAL));
        board2.add(Ship.create(new Point(1, 8), 1, Orientation.HORIZONTAL));
        board2.add(Ship.create(new Point(4, 7), 3, Orientation.VERTICAL));
        board2.add(Ship.create(new Point(8,9), 1, Orientation.HORIZONTAL));
        board2.add(Ship.create(new Point(2, 10), 1, Orientation.HORIZONTAL));
        return board2;
    }
}
