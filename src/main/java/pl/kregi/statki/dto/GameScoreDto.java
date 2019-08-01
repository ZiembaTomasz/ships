package pl.kregi.statki.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameScoreDto {
    private GameStatus gameStatus;
    private int yourScore;
    private int opponentScore;
}
