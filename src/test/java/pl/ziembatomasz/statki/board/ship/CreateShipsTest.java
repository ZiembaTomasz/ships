package pl.ziembatomasz.statki.board.ship;

import org.junit.Test;
import pl.ziembatomasz.statki.board.Orientation;
import pl.ziembatomasz.statki.board.Point;
import pl.ziembatomasz.statki.board.Ship;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateShipsTest {

    private static Point makePoint(final int x, final int y) {
        return new Point(x, y);
    }

    @Test
    public void shouldCreateShipOfSizeOne() {
        // given
        final Point startPoint = makePoint(1, 1);

        // when
        final Ship result = Ship.create(startPoint, 1, Orientation.HORIZONTAL);

        // then
        assertThat(result.getPositions().size()).isEqualTo(1);
        assertThat(result.getPositions().iterator().next()).isEqualTo(makePoint(1, 1));
    }

    @Test
    public void shouldCreateShipOfSizeTwoHorizontal() {
        // given
        final Point startPoint = makePoint(1, 1);

        // when
        final Ship result = Ship.create(startPoint, 2, Orientation.HORIZONTAL);

        // then
        assertThat(result.getPositions().size()).isEqualTo(2);
        assertThat(result.getPositions()).containsExactlyInAnyOrder(makePoint(1, 1), makePoint(1, 2));
    }

    @Test
    public void shouldCreateShipOfSizeTwoVertical() {
        // given
        final Point startPoint = makePoint(1, 1);

        // when
        final Ship result = Ship.create(startPoint, 2, Orientation.VERTICAL);

        // then
        assertThat(result.getPositions().size()).isEqualTo(2);
        assertThat(result.getPositions()).containsExactlyInAnyOrder(makePoint(1, 1), makePoint(2, 1));
    }

    @Test
    public void shouldCreateShipOfSizeThreeHorizontal() {
        // given
        final Point startPoint = makePoint(1, 1);

        // when
        final Ship result = Ship.create(startPoint, 3, Orientation.HORIZONTAL);

        // then
        assertThat(result.getPositions()).containsExactlyInAnyOrder(makePoint(1, 1), makePoint(1, 2), makePoint(1, 3));
    }

    @Test
    public void shouldCreateShipOfSizeThreeVertical() {
        // given
        final Point startPoint = makePoint(1, 1);

        // when
        final Ship result = Ship.create(startPoint, 3, Orientation.VERTICAL);

        // then
        assertThat(result.getPositions()).containsExactlyInAnyOrder(makePoint(1, 1), makePoint(2, 1), makePoint(3, 1));
    }
}
