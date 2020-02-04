package pl.ziembatomasz.statki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.ziembatomasz.statki.dto.CreateGameDto;
import pl.ziembatomasz.statki.dto.GameStateDto;
import pl.ziembatomasz.statki.game.Game;
import pl.ziembatomasz.statki.service.GameService;

import java.util.UUID;

@RestController
public class GameController {

    private static final String AUTH_TOKEN = "Auth-Token";
    private static final String SET_AUTH_TOKEN = "Set-Auth-Token";
    private static final String INVITE_URL = "/game/{id}/join";

    private String joinUrl;
    private GameService gameService;

    @Autowired
    public GameController(@Value("${statki.url}") String statkiUrl, GameService gameService) {
        this.joinUrl = statkiUrl + INVITE_URL;
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public ResponseEntity<CreateGameDto> createGame(
            @RequestHeader(value = AUTH_TOKEN, required = false) UUID playersToken) {
        if (playersToken == null) {
            playersToken = UUID.randomUUID();
        }
        Game game = gameService.createGame(playersToken);
        CreateGameDto body = CreateGameDto.builder()
                .inviteUrl(inviteUrl(game))
                .build();
        return ResponseEntity.ok()
                .header(SET_AUTH_TOKEN, game.firstPlayer().toString())
                .body(body);
    }

    @PostMapping(INVITE_URL)
    public ResponseEntity<GameStateDto> join(@PathVariable Long id,
                                             @RequestHeader(value = AUTH_TOKEN, required = false) UUID playersToken) {
        if (playersToken == null) {
            playersToken = UUID.randomUUID();
        }
        GameStateDto state = gameService.join(id, playersToken);
        return ResponseEntity.ok()
                .body(state);
    }

    private String inviteUrl(Game game) {
        return joinUrl.replace("{id}", String.valueOf(game.getId()));
    }
}
