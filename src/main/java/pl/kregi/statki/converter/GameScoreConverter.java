package pl.kregi.statki.converter;

import org.springframework.stereotype.Component;
import pl.kregi.statki.board.Board;
import pl.kregi.statki.dto.GameScoreDto;
import pl.kregi.statki.game.Game;
import pl.kregi.statki.repo.GameRepo;
import pl.kregi.statki.service.GameService;

import java.util.Map;
import java.util.UUID;

@Component
public class GameScoreConverter {
    GameService gameService;
    GameRepo gameRepo;
    public GameScoreDto convertScore(Long id, UUID playersToken){
        Game game = gameRepo.findOne(id);
        Board defenderBoard = null;
        Board attackerBoard = null;
        Map<UUID, Board>boards = game.getBoards();
        for(Map.Entry<UUID, Board> entry: boards.entrySet()){
            if(!entry.getKey().equals(playersToken)){
                defenderBoard = entry.getValue();
            }
            if(entry.getKey().equals(playersToken)){
                attackerBoard = entry.getValue();
            }
        }

        return GameScoreDto.builder()
                .gameStatus(gameService.status(id, playersToken))
                .opponentScore(defenderBoard.counterPoints())
                .yourScore(attackerBoard.counterPoints())
                .build();
    }
}
