package pl.kregi.statki.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;
import pl.kregi.statki.board.Board;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Game {
    private Set<UUID> players;

    @Getter
    @Setter
    private Long id;
    @Getter
    private int numberOfPlayers;
    @Getter
    private UUID currentPlayer;
    @Getter
    private Board board;

    private Game(int numberOfPlayers, UUID firstPlayer, Board board) {
        this.numberOfPlayers = numberOfPlayers;
        this.board = board;
        players = new HashSet<>();
        players.add(firstPlayer);
        currentPlayer = firstPlayer();
    }

    public static Game create(int numberOfPlayers, Player firstPlayer, Board board) {
        return new Game(numberOfPlayers, firstPlayer.getId(), board);
    }

    public synchronized void join(Player player) {
        Assert.notNull(player, "Joining player cannot be null");
        Assert.notNull(player.getId(), "Joining player id cannot be null");
        if (players.size() >= numberOfPlayers) {
            throw new IllegalStateException("Game is full");
        }
        players.add(player.getId());
    }

    public UUID firstPlayer() {
        return players.iterator().next();
    }

    public Set<UUID> getPlayers() {
        return Collections.unmodifiableSet(players);
    }
}
