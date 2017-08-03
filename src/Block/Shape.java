package Block;
/*http://tetris.wikia.com/wiki/SRS*/

import Game.Board;

import java.awt.*;

public abstract class Shape {

    int coorX,coorY;

    /**
     *
     */

    public Shape() {
        coorX = 35;
        coorY = 0;
    }

    public abstract void rotateClockWise();

    public abstract void rotateCounterClockWise();

    public abstract void setTranspose();

    public abstract int[][] getTranspose();

    public abstract void update();

    public abstract void setDeltaX(int deltaX);

    public abstract void render(Graphics g); // find how to scale the array with the board

}
