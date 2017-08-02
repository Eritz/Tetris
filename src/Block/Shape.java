package Block;
/*http://tetris.wikia.com/wiki/SRS*/

import java.awt.*;

public abstract class Shape {

    private boolean landed; // false would be the focus, true is when it's landed
    int coorX,coorY;

    /**
     *
     */

    public Shape() {
        coorX = 3;
        coorY = 0;
    }

    public abstract void rotateClockWise();

    public abstract void rotateCounterClockWise();

    public abstract void setTranspose();

    public abstract int[][] getTranspose();

    public abstract void update();

    public abstract void render(Graphics g);

}
