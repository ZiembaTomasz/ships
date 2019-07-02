package pl.kregi.statki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HitDto {
    private boolean hit;
    private int shipSize;
    private boolean isDestroyed;

}
