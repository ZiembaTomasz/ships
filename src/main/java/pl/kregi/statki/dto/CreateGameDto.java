package pl.kregi.statki.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateGameDto {

    private String inviteUrl;
}
