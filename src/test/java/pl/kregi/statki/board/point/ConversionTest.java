package pl.kregi.statki.board.point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kregi.statki.board.Point;
import pl.kregi.statki.service.GameService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConversionTest {
    @InjectMocks
    GameService gameService;

    @Test
    public void shouldConverseStringToPoint() {
        // given
        String positionDto = "A5";
        //when
        Point point = gameService.conversionToPoint(positionDto);
        //then
        assertThat(point.getX()).isEqualTo(1);
        assertThat(point.getY()).isEqualTo(5);
    }
}
