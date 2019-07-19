package pl.kregi.statki.converter;

import org.springframework.stereotype.Component;
import pl.kregi.statki.board.Point;
import pl.kregi.statki.board.Ship;
import pl.kregi.statki.dto.HitDto;

@Component
public class HitDtoConverter {
    public HitDto convertHit(Ship ship, Point point){
        if(ship == null){
           return HitDto.builder()
                    .hit(false)
                   .build();
        }
        return HitDto.builder()
                .hit(ship.niceShot(point))
                .shipSize(ship.getPositions().size())
                .isDestroyed(ship.isSunk())
                .build();
    }
}
