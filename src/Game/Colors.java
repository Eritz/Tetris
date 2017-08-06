package Game;

import java.awt.*;

public enum Colors {
    PINK(1, Color.PINK),
    CYAN(2, Color.CYAN),
    GREEN(3, Color.GREEN),
    YELLOW(4, Color.YELLOW),
    RED(5, Color.RED),
    MAGENTA(6, Color.MAGENTA),
    ORANGE(7, Color.ORANGE),
    BLACK(8, Color.BLACK);

    private final int num;
    private final Color color;

    private Colors(int value, Color col) {
        this.num = value;
        this.color = col;
    }

    public int getColorNum() {
        return num;
    }

    public Color getColor() {
        return color;
    }
}
