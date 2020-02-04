package pl.ziembatomasz.statki.board.point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.ziembatomasz.statki.board.Orientation;
import pl.ziembatomasz.statki.board.Point;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class NextPointTest {

    private Point start;
    private Orientation direction;
    private Point expected;

    public NextPointTest(Point start, Orientation direction, Point expected) {
        this.start = start;
        this.direction = direction;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {makePoint(1, 1), Orientation.HORIZONTAL, makePoint(1, 2)},
                {makePoint(1, 2), Orientation.HORIZONTAL, makePoint(1, 3)},
                {makePoint(1, 1), Orientation.VERTICAL, makePoint(2, 1)},
                {makePoint(2, 1), Orientation.VERTICAL, makePoint(3, 1)},
        });
    }

    private static Point makePoint(int x, int y) {
        return new Point(x, y);
    }

    @Test
    public void shouldCreateNextPoint() {
        // given

        // when
        final Point point = Point.next(start, direction);

        // then
        assertThat(point).isEqualTo(expected);
    }
}
