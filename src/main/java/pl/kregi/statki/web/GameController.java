package pl.kregi.statki.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kregi.statki.board.NotPlayerTurnException;
import pl.kregi.statki.dto.*;
import pl.kregi.statki.game.Game;
import pl.kregi.statki.repo.PlayerRepo;
import pl.kregi.statki.service.GameService;

import java.util.UUID;

@RestController
public class GameController {

    private static final String AUTH_TOKEN = "Auth-Token";
    private static final String SET_AUTH_TOKEN = "Set-Auth-Token";
    private static final String INVITE_URL = "/game/{id}/join";

    private String joinUrl;
    private GameService gameService;
    private PlayerRepo playerRepo;


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

    @PutMapping("/game/{id}")
    public ResponseEntity<HitDto> shot(@PathVariable Long id,
                                       @RequestHeader(value = AUTH_TOKEN, required = true) UUID playersToken,
                                       @RequestBody PositionDto point) {
        if (gameService.isPlayerMove(playersToken, id)) {
            HitDto hit = gameService.shot(gameService.conversionToPoint(point.getPosition()), id, playersToken);
            return ResponseEntity.ok()
                    .body(hit);
        } else {
            throw new NotPlayerTurnException();
        }
    }
    @GetMapping("/game/{id}")
    public ResponseEntity<GameScoreDto>status(@PathVariable Long id,
                                              @RequestHeader(value = AUTH_TOKEN, required = true) UUID playersToken){
        GameScoreDto gameScoreDto = gameService.score(id, playersToken);
        return ResponseEntity.ok()
                .body(gameScoreDto);
    }
}
