package Block;

import Game.Board;
import Game.Tetromino;

import java.awt.*;

public class L extends Shape {

    Tetromino shape;
    private int[][] transpose;
    public int[][] coords;
    private Board gameboard;
    private Color color;
    private int deltaX;

    /* L shape
     * [1 0 0]
     * [1 1 1]
     */

    public L(Board b) {
        shape = Tetromino.L;

        this.color = Color.BLUE;
        this.gameboard = b;

        coords = new int[][]{ {1,0,0}, {1,1,1} };

    }

    /* Gets the transpose of the current shape,
     * then reverse the elements in the row to get the rotated CW shape
     */
    public void rotateClockWise() {
        setTranspose();
        int[][] tran = getTranspose();

        for (int i = 0; i < tran.length; i++) {
            for (int j = 0; j < tran[0].length/2; j++) {
                int temp = tran[i][j];
                tran[i][j] = tran[i][tran[i].length - j - 1];
                tran[i][tran[i].length - 1] = temp;
            }
        }

        if (coorX + 35*(tran[0].length) > 350) {
            return;
        }
        coords = tran;
    }

    /* Gets the transpose of the current shape,
     * then reverse the elements in the column to get the rotated CCW shape
     */
    public void rotateCounterClockWise() {
        setTranspose();
        int[][] tran = getTranspose();

        for (int i = 0; i < tran.length/2; i++) {
            for (int j = 0; j < tran[i].length; j++) {
                int temp = tran[i][j];
                tran[i][j] = tran[tran.length - i - 1][j];
                tran[tran.length - i - 1][j] = temp;
            }
        }

        if (coorX + 35*(tran[0].length) > 350) {
            return;
        }
        coords = tran;
    }

    /* Change the rows to columns. Create a temporary 3x3 to hold the new value
     * and then set the temporary matrix's value in the tranpose
     */
    public void setTranspose() {
        int row = coords.length;
        int col = coords[0].length;

        int[][] temp = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp[j][i] = coords[i][j];
            }
        }
        transpose = temp;
    }

    /* Returns the current matrix's transpose */
    public int[][] getTranspose() {
        return transpose;
    }

    /* This method is called by the board's timer. It will run this method and repaint the screen.
     * Shift the X coordinates by deltaX only if no collision.
     */
    public void update() {
        long current = System.currentTimeMillis();

        if (!(35*coorY + 35 + 35*(coords.length) > 560)) {
            if (current - lastTime > CURRENTSPEED) {
                coorY++;
                System.out.println(35*coorY + 35 + 35*(coords.length));
                lastTime = System.currentTimeMillis();
            }
        }

        if (!(coorX + deltaX + 35 * (coords[0].length) > 350) && !(coorX + deltaX < 0)) {
            coorX += deltaX;
        }

        this.deltaX = 0; // return it back to 0
    }

    /* Pressing > or < will make the entire piece shift by deltaX*/
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }


    /* Every time there is a 1 in the dimension for L, fill it in color
     * and draw the rectangular borders. The order of the code matters in setting the colors.
     * Set the color first and then make what the color is for.
     */
    public void render(Graphics g) {
        for (int i=0; i< coords.length; i++) {
            for (int j=0; j <coords[i].length; j++) {
                if (coords[i][j] != 0) {
                    g.setColor(color);
                    g.fillRect(j*35 + coorX,i*35 + 35*coorY,35,35); //reverse the coordinates
                    g.setColor(Color.BLACK);
                    g.drawRect(j*35 + coorX,i*35 + 35*coorY,35,35);

                }
            }
        }
    }
}
