package pl.ziembatomasz.statki.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder
public class GameStateDto {
    private int numberOfPlayers;
    private Set<UUID> players;
    private UUID currentPlayer;
}
