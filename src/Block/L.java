package Block;

import Game.Board;
import Game.Tetromino;

import java.awt.*;

public class L extends Shape {

    Tetromino shape;
    private int[][] transpose = new int[3][3];
    private int[][] coords;
    private Board gameboard;
    private Color color;
    private int deltaX;

    /* L shape
     * [1 0 0]
     * [1 1 1]
     * [0 0 0]
     */

    public L(Board b, int[][] coords) {
        shape = Tetromino.L;

        this.color = Color.BLUE;
        this.gameboard = b;
        this.coords = coords;

        gameboard.gameBoard[0][0] = 1; gameboard.gameBoard[0][1] = 0; gameboard.gameBoard[0][2] = 0;
        for (int i=0; i < 3; i++) {
            gameboard.gameBoard[1][i] = 1;
            gameboard.gameBoard[2][i] = 0;
        }
    }

    /* Gets the transpose of the current shape,
     * then reverse the elements in the row to get the rotated CW shape
     */
    public void rotateClockWise() {
        setTranspose();
        int[][] tran = getTranspose();

        for (int i=0; i<3; i++) {
            for (int j=0; j<1; j++) {
                int temp = tran[i][j];
                tran[i][j] = tran[i][tran[i].length - 1];
                tran[i][tran[i].length - 1] = temp;
            }
        }
        gameboard.gameBoard = tran;
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
        gameboard.gameBoard = tran;
    }

    /* Change the rows to columns. Create a temporary 3x3 to hold the new value
     * and then set the temporary matrix's value in the tranpose
     */
    public void setTranspose() {
        int [][] temp = new int[3][3];
        for (int i =0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[j][i] = gameboard.gameBoard[i][j];
            }
        }
        transpose = temp;
    }

    /* Returns the current matrix's transpose */
    public int[][] getTranspose() {
        return transpose;
    }

    // shift the Xcoordinates by deltaX only if no collision
    // have to fix the transpose because it'll make it 3 always rather than 10
    public void update() {
        if (!(coorX + deltaX + 3 > 350) && !(coorX + deltaX < 0)) {
            coorX+=deltaX;
        }
        this.deltaX = 0; // return it back to 0
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /* Every time there is a 1 in the dimension for L, fill it in color
     * and draw the rectangular borders. The order of the code matters in setting the colors.
     * Set the color first and then make what the color is for.
     */
    public void render(Graphics g) { // find how to scale the array with the board
        for (int i=0; i<3; i++) {
            for (int j=0; j <gameboard.gameBoard[i].length; j++) {
                if (gameboard.gameBoard[i][j] != 0) {
                    g.setColor(color);
                    g.fillRect(i*35 + coorX,j*35 + coorY,35,35);
                    g.setColor(Color.BLACK);
                    g.drawRect(i*35 + coorX,j*35 + coorY,35,35);

                }
            }
        }
    }
}
