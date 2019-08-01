package pl.kregi.statki.converter;

import org.springframework.stereotype.Component;
import pl.kregi.statki.dto.GameScoreDto;

import java.util.UUID;

@Component
public class GameScoreConverter {
    GameScoreDto gameScoreDto;
    public GameScoreDto convertScore(Long id, UUID playersToken,){

        return GameScoreDto.builder()
                .gameStatus(gameScoreDto.getGameStatus())
                .opponentScore()
    }
}
