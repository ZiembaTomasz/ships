package pl.kregi.statki.board;

import org.junit.Test;
import pl.kregi.statki.game.Game;
import pl.kregi.statki.game.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Collections.newSetFromMap;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void shouldCreateBoardGivenOneSizedShip() {
        // given
        final Ship ship = makeShip(point(1, 1), 1, Orientation.VERTICAL);

        // when
        final Board board = Board.withShips(singleton(ship), 2);

        // then
        assertThat(board.hasOnPosition(point(1, 1))).isTrue();
        assertThat(board.hasOnPosition(point(1, 2))).isFalse();
        assertThat(board.hasOnPosition(point(2, 1))).isFalse();
        assertThat(board.hasOnPosition(point(2, 2))).isFalse();
    }

    @Test
    public void shouldCreateBoardGivenTwoSizedShip() {
        // given
        final Ship ship = makeShip(point(1, 1), 2, Orientation.VERTICAL);

        // when
        final Board board = Board.withShips(singleton(ship), 2);

        // then
        assertThat(board.hasOnPosition(point(1, 1))).isTrue();
        assertThat(board.hasOnPosition(point(1, 2))).isTrue();
        assertThat(board.hasOnPosition(point(2, 1))).isFalse();
    }

    @Test
    public void shouldCreateBoardGivenThreeSizedShip() {
        // given
        final Ship ship = makeShip(point(1, 1), 3, Orientation.HORIZONTAL);

        // when
        final Board board = Board.withShips(singleton(ship), 3);

        // then
        assertThat(board.hasOnPosition(point(1, 1))).isTrue();
        assertThat(board.hasOnPosition(point(2, 1))).isTrue();
        assertThat(board.hasOnPosition(point(3, 1))).isTrue();
        assertThat(board.hasOnPosition(point(1, 2))).isFalse();
        assertThat(board.hasOnPosition(point(1, 3))).isFalse();
        assertThat(board.hasOnPosition(point(2, 3))).isFalse();
        assertThat(board.hasOnPosition(point(2, 2))).isFalse();
        assertThat(board.hasOnPosition(point(3, 2))).isFalse();
        assertThat(board.hasOnPosition(point(3, 3))).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenShipOutOfBoardBounds() {
        // given
        final Ship ship = makeShip(point(4, 1), 1, Orientation.VERTICAL);

        // when
        Board.withShips(singleton(ship), 3);
    }

    @Test(expected = ShipCollisionException.class)
    public void shouldThrowExceptionGivenAddingShipOverOther() {
        // given
        final Ship ship1 = makeShip(point(1, 1), 1, Orientation.VERTICAL);
        final Ship ship2 = makeShip(point(1, 1), 1, Orientation.VERTICAL);
        final Board board = Board.withShips(singleton(ship1), 3);

        // when
        board.add(ship2);
    }

    @Test(expected = ShipCollisionException.class)
    public void shouldThrowExceptionGivenAddingShipNextToOther() {
        // given
        final Ship ship1 = makeShip(point(1, 1), 1, Orientation.VERTICAL);
        final Ship ship2 = makeShip(point(2, 1), 1, Orientation.VERTICAL);
        final Board board = Board.withShips(singleton(ship1), 3);

        // when
        board.add(ship2);
    }

    @Test(expected = ShipCollisionException.class)
    public void shouldThrowExceptionGivenAddingShipNextToOtherDifferentSizes() {
        // given
        final Ship ship1 = makeShip(point(3, 3), 1, Orientation.VERTICAL);
        final Ship ship2 = makeShip(point(2, 1), 3, Orientation.VERTICAL);
        final Board board = Board.withShips(singleton(ship1), 3);

        // when
        board.add(ship2);
    }
    @Test(expected = ShipCollisionException.class)
    public void shouldCreateBoards(){
        //given
        Player player = new Player(UUID.randomUUID());
        SampleBoardFactory sampleBoardFactory = new SampleBoardFactory();
        Board board = sampleBoardFactory.createFirstBoard();
        Map<UUID, Board>mapka = new HashMap<>();
        UUID id = player.getId();
        mapka.put(id, board);
        //when
        Game game = Game.create(2, player, board);

        assertEquals(game.getBoards().size(), mapka.size());

    }

    private Ship makeShip(final Point position, final int size, final Orientation orientation) {
        return Ship.create(position, size, orientation);
    }

    private Point point(final int x, final int y) {
        return new Point(x, y);
    }
}
