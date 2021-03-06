package pl.ziembatomasz.statki.board.point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.ziembatomasz.statki.board.Point;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class PointCorrectTest {

    private int x;
    private int y;

    public PointCorrectTest(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1}, {2, 2}, {0, 0}, {1, 0}, {0, 1}
        });
    }

    @Test
    public void shouldCreatePointGivenCorrectData() {
        // given

        // when
        final Point point = new Point(x, y);

        // then
        assertThat(point.getX()).isEqualTo(x);
        assertThat(point.getY()).isEqualTo(y);
    }

}
