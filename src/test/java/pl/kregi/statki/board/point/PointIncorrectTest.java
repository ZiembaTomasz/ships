package pl.kregi.statki.board.point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.kregi.statki.board.Point;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PointIncorrectTest {

    private int x;
    private int y;

    public PointIncorrectTest(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {- 1, 1 }, {1, -1}
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCreatePointGivenCorrectData() {
        // given

        // when
        new Point(x, y);
    }

}
