package Block;

import Game.Tetromino;

import java.util.Arrays;

public class L extends Shape {

    Tetromino shape;
    private int[][] transpose = new int[3][3];
    int[][] gridLocation = {};

    /* L shape
     * [1 0 0]
     * [1 1 1]
     * [0 0 0]
     */

    public L(int b_x, int b_y) {
        super(b_x,b_y);
        shape = Tetromino.L;
        dim[0][0] = 1; dim[0][1] = 0; dim[0][2] = 0;
        for (int i=0; i < 3; i++) {
            dim[1][i] = 1;
            dim[2][i] = 0;
        }
    }

    /* Gets the transpose of the current shape,
     * then use it to multiply with the mirror Identity matrice.
     * Create a temporary array[][] to store the addition and end by
     * setting it to the real shape's dimension.
     */
    public void rotateClockWise() {
        int[][] temp = new int[3][3];
        setTranspose();
        int[][] tran = getTranspose();

        for (int k = 0; k<3; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[k][i] += tran[k][j] * mirror[j][i];
                }
            }
        }
        dim = temp;
        System.out.println(Arrays.deepToString(dim));
    }

    /* Gets the transpose of the current shape,
     * then reverse the elements in the column to get the rotated CCW shape
     */
    public void rotateCounterClockWise() {
        setTranspose();
        int[][] tran = getTranspose();

        for (int i=0; i<1; i++) {
            for (int j=0; j<3; j++) {
                int temp = tran[i][j];
                tran[i][j] = tran[tran[i].length - i - 1][j];
                tran[tran[i].length - i - 1][j] = temp;
            }
        }
        dim = tran;

    }

    public void setTranspose() {
        for (int i =0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transpose[j][i] = dim[i][j];
            }
        }
    }

    public int[][] getTranspose() {
        return transpose;
    }
}

