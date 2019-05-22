package pl.kregi.statki.board;

import lombok.Getter;

@Getter
public enum Orientation {
    VERTICAL(new Point(0, 1)), HORIZONTAL(new Point(1, 0));

    private Point value;

    Orientation(final Point point) {
        this.value = point;
    }
}
