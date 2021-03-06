package pl.ziembatomasz.statki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.ziembatomasz.statki.converter.GameStateConverter;
import pl.ziembatomasz.statki.dto.GameStateDto;
import pl.ziembatomasz.statki.game.Game;
import pl.ziembatomasz.statki.game.Player;
import pl.ziembatomasz.statki.repo.GameRepo;
import pl.ziembatomasz.statki.repo.PlayerRepo;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepo gameRepo;
    private PlayerRepo playerRepo;
    private GameStateConverter gameStateConverter;

    public Game createGame(UUID playersToken) {
        Player player = player(playersToken);
        Game game = Game.create(2, player);
        gameRepo.save(game);
        playerRepo.save(player);
        return game;
    }

    public GameStateDto join(Long id, UUID playersToken) {
        Game game = Optional.ofNullable(gameRepo.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " does not exist"));
        Player player = player(playersToken);
        game.join(player);
        playerRepo.save(player);
        return gameStateConverter.convert(game);
    }

    private Player player(UUID playersToken) {
        Assert.notNull(playersToken, "Token cannot be null");
        return Optional.ofNullable(playerRepo.findOne(playersToken))
                .orElse(new Player(UUID.randomUUID()));
    }
}
