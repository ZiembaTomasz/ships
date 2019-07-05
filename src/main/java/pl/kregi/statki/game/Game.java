package pl.kregi.statki.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;
import pl.kregi.statki.board.Board;
import pl.kregi.statki.board.Point;
import pl.kregi.statki.board.Ship;

import java.util.*;

public class Game {
     Set<UUID> players;


    @Getter
    @Setter
    private Long id;
    @Getter
    private int numberOfPlayers;
    @Setter
    @Getter
    private UUID currentPlayer;
    @Getter
    private Map<UUID, Board>boards;

     public Game(int numberOfPlayers, UUID firstPlayer, Map<UUID, Board>boards) {
        this.numberOfPlayers = numberOfPlayers;
        this.boards = boards;
        players = new HashSet<>();
        players.add(firstPlayer);
        currentPlayer = firstPlayer();
    }

    public static Game create(int numberOfPlayers, Player firstPlayer, Board firstPlayerBoard) {
        Map<UUID, Board> boardMap = new HashMap<>();
        boardMap.put(firstPlayer.getId(), firstPlayerBoard);
        return new Game(numberOfPlayers, firstPlayer.getId(), boardMap);
    }

    public synchronized void join(Player player, Board playerBoard) {
        Assert.notNull(player, "Joining player cannot be null");
        Assert.notNull(player.getId(), "Joining player id cannot be null");
        if (players.size() >= numberOfPlayers) {
            throw new IllegalStateException("Game is full");
        }
        players.add(player.getId());
        boards.put(player.getId(), playerBoard);
        currentPlayer = player.getId();
    }

    public UUID firstPlayer() {
        return players.iterator().next();
    }

    public Set<UUID> getPlayers() {
        return Collections.unmodifiableSet(players);
    }
    public Ship shot(Point point, UUID attackingPlayer){
         UUID defenderPlayer = null;

         for(Map.Entry<UUID, Board> entry : boards.entrySet()){
             if(!entry.getKey().equals(attackingPlayer)){
                 defenderPlayer = entry.getKey();
             }
         }
         Board board = getBoards().get(defenderPlayer);
         if(board.shot(point).niceShot(point)){
             setCurrentPlayer(attackingPlayer);
        }
         else{
             setCurrentPlayer(defenderPlayer);
         }
       return board.shot(point);
    }
}
