package Block;
/*http://tetris.wikia.com/wiki/SRS*/

import Game.Board;

import java.awt.*;

public abstract class Shape {

    int coorX,coorY;
    long NORMALSPEED = 1000, CURRENTSPEED; //1000ms
    long lastTime;


    public Shape() {
        coorX = 175; // just initial displacement
        coorY = 0;
        CURRENTSPEED = NORMALSPEED;
        this.lastTime = System.currentTimeMillis();
    }

    public abstract void rotateClockWise();

    public abstract void rotateCounterClockWise();

    public abstract void setTranspose();

    public abstract int[][] getTranspose();

    public abstract void update();

    public void setCurrentSpeed() {
        CURRENTSPEED = 300;
    }

    public void resetCurrentSpeed() {
        CURRENTSPEED = NORMALSPEED;
    }

    public abstract void setDeltaX(int deltaX);

    public abstract void render(Graphics g);

}
