package pl.kregi.statki.converter;

import org.springframework.stereotype.Component;
import pl.kregi.statki.dto.GameStateDto;
import pl.kregi.statki.game.Game;

@Component
public class GameStateConverter {

    public GameStateDto convert(Game game) {
        return GameStateDto.builder()
                .currentPlayer(game.getCurrentPlayer())
                .numberOfPlayers(game.getNumberOfPlayers())
                .players(game.getPlayers())
                .build();
    }
}
