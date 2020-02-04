package pl.ziembatomasz.statki.board;

import lombok.Getter;

@Getter
public enum Orientation {
    VERTICAL(new Point(1, 0)), HORIZONTAL(new Point(0, 1));

    private Point value;

    Orientation(final Point point) {
        this.value = point;
    }
}
