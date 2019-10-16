package pl.kregi.statki.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
@Setter
public class GameScoreDto {
    private GameStatus gameStatus;
    private int yourScore;
    private int opponentScore;
}
