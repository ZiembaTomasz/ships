package pl.kregi.statki.game;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kregi.statki.board.Board;
import pl.kregi.statki.dto.GameScoreDto;
import pl.kregi.statki.dto.GameStatus;
import pl.kregi.statki.repo.GameRepo;
import pl.kregi.statki.service.GameService;

import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.kregi.statki.dto.GameStatus.YOUR_TURN;


@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @InjectMocks
    GameService gameService;
    @Mock
    Game game;
    @Mock
    GameRepo gameRepo;
    @Mock
    Board board;
    @Test
    public void shouldGetGameStatusAwaitingPlayers(){
        when(game.getNumberOfPlayers()).thenReturn(1);
        when(gameRepo.findOne(100L)).thenReturn(game);

        GameStatus result = gameService.status(100L, UUID.randomUUID());
        Assertions.assertThat(result).isEqualTo(GameStatus.AWAITING_PLAYERS);
    }
    @Test
    public void shouldGetGameStatusYourTurn(){
        UUID firstPlayer = UUID.randomUUID();
        when(game.getNumberOfPlayers()).thenReturn(2);
        when(game.getCurrentPlayer()).thenReturn(firstPlayer);
        when(gameRepo.findOne(100L)).thenReturn(game);

        GameStatus result = gameService.status(100L, firstPlayer);
        Assertions.assertThat(result).isEqualTo(GameStatus.YOUR_TURN);
    }
    @Test
    public void shouldGetGameStatusYouLost(){
        UUID firstPlayer = UUID.randomUUID();
        Map<UUID, Board> boards = game.getBoards();

        boards.put(firstPlayer, board);

        when(game.getNumberOfPlayers()).thenReturn(2);
        when(gameRepo.findOne(100L)).thenReturn(game);
        when(game.getBoards()).thenReturn(boards);
        when(board.allSunk()).thenReturn(true);

        GameStatus result = gameService.status(100L, firstPlayer);
        Assertions.assertThat(result).isEqualTo(GameStatus.YOU_LOST);

    }
}
