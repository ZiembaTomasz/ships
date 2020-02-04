package pl.ziembatomasz.statki.repo;

import org.springframework.stereotype.Repository;
import pl.ziembatomasz.statki.game.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class PlayerRepo {

    private Map<UUID, Player> players = new HashMap<>();


    public Player save(Player player) {
        player.setId(UUID.randomUUID());
        players.put(player.getId(), player);
        return player;
    }

    public Player findOne(UUID id) {
        return players.get(id);
    }
}
