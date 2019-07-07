package pl.kregi.statki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class HitDto {
    private boolean hit;
    private int shipSize;
    private boolean isDestroyed;

}
