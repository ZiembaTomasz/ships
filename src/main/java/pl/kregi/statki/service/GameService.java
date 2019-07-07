package pl.kregi.statki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.kregi.statki.board.Point;
import pl.kregi.statki.board.SampleBoardFactory;
import pl.kregi.statki.board.Ship;
import pl.kregi.statki.converter.GameStateConverter;
import pl.kregi.statki.converter.HitDtoConverter;
import pl.kregi.statki.dto.GameStateDto;
import pl.kregi.statki.dto.HitDto;
import pl.kregi.statki.game.Game;
import pl.kregi.statki.game.Player;
import pl.kregi.statki.repo.GameRepo;
import pl.kregi.statki.repo.PlayerRepo;

import java.util.*;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepo gameRepo;
    private PlayerRepo playerRepo;
    private GameStateConverter gameStateConverter;
    private HitDtoConverter hitDtoConverter;



    public Game createGame(UUID playersToken) {
        Player player = player(playersToken);
        SampleBoardFactory sampleBoardFactory = new SampleBoardFactory();
        Game game = Game.create(2, player, sampleBoardFactory.createFirstBoard());
        gameRepo.save(game);
        playerRepo.save(player);
        return game;
    }

    public GameStateDto join(Long id, UUID playersToken) {
        Game game = Optional.ofNullable(gameRepo.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " does not exist"));
        Player player = player(playersToken);
        SampleBoardFactory sampleBoardFactory = new SampleBoardFactory();
        game.join(player, sampleBoardFactory.createSecondBoard());
        playerRepo.save(player);
        return gameStateConverter.convert(game);
    }

    private Player player(UUID playersToken) {
        Assert.notNull(playersToken, "Token cannot be null");
        return Optional.ofNullable(playerRepo.findOne(playersToken))
                .orElse(new Player(UUID.randomUUID()));
    }
    public boolean isPlayerMove(UUID player, Long id){
        Game game = Optional.ofNullable(gameRepo.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " does not exist"));
        if(player.equals(game.getCurrentPlayer())){
            return true;
        }
        return false;
    }
    public HitDto shot(Point point, Long id, UUID atackerPlayer){
        Game game = Optional.ofNullable(gameRepo.findOne(id
        )).orElseThrow(() -> new IllegalArgumentException("Game with id " + id +" does not exist"));
        Ship ship = game.shot(point, atackerPlayer);
        gameRepo.save(game);
        return hitDtoConverter.convertHit(ship, point);
    }
    public Point conversionToPoint(String positionDto){
        int x = positionDto.indexOf(0);
        int y = positionDto.indexOf(1);
        Point point = new Point(x, y);
        return point;

    }
}
