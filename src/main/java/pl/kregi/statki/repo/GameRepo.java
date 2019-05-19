package pl.kregi.statki.repo;

import org.springframework.stereotype.Repository;
import pl.kregi.statki.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GameRepo {
    private Map<Long, Game> games = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong();

    public Game save(Game game) {
        Long id = idGenerator.incrementAndGet();
        game.setId(id);
        games.put(id, game);
        return game;
    }

    public Game findOne(Long id) {
        return games.get(id);
    }
}
