package pl.ziembatomasz.statki.converter;

import org.springframework.stereotype.Component;
import pl.ziembatomasz.statki.dto.GameStateDto;
import pl.ziembatomasz.statki.game.Game;

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
