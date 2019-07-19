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
    private Point point;



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
        String a = positionDto.substring(1);
        String b = positionDto.substring(2);
        int x = 0;
        int y = 0;
        Point point = new Point(x, y);
        if(a.equals("A")){
            x = 1;
        }
        if(a.equals("B")){
            x = 2;
        }
        if(a.equals("C")){
            x = 3;
        }
        if(a.equals("D")){
            x = 4;
        }
        if(a.equals("E")){
            x = 5;
        }
        if(a.equals("F")){
            x = 6;
        }
        if(a.equals("G")){
            x = 7;
        }
        if(a.equals("H")){
            x = 8;
        }
        if(a.equals("I")){
            x = 9;
        }
        Integer.parseInt(b);
        return  point;
    }
}
