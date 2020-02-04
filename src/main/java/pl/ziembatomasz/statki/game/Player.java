package pl.ziembatomasz.statki.game;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Player {

    @Setter
    private UUID id;

    public Player(UUID id) {
        this.id = id;
    }
}
