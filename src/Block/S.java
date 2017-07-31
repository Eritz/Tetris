package Block;

import Game.Tetromino;

public class S extends Shape{

    Tetromino shape;

    public S(int b_x, int b_y) {
        super(b_x,b_y);
        shape = Tetromino.S;
    }

    @Override
    public void rotateClockWise() {

    }

    @Override
    public void rotateCounterClockWise() {

    }

    @Override
    public void setTranspose() {

    }

    @Override
    public int[][] getTranspose() {
        return new int[0][];
    }
}
