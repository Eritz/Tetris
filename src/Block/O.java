package Block;

import Game.Tetromino;

public class O extends Shape {

    Tetromino shape;

    public O (int b_x, int b_y) {
        super(b_x,b_y);
        shape = Tetromino.O;
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
