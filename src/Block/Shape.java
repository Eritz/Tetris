package Block;

import java.awt.*;

public abstract class Shape {

    Color borders = Color.BLACK;
    int coorX,coorY;
    long NORMALSPEED = 1000, CURRENTSPEED; //1000ms
    long lastTime;

    private int[][] transpose;
    public int[][] coords;
    private int deltaX;
    private boolean collision;



    public Shape() {
        coorX = 175; // just initial displacement
        coorY = 0;
        CURRENTSPEED = NORMALSPEED;
        this.lastTime = System.currentTimeMillis();
    }

    /* Gets the transpose of the current shape, then reverse the elements in the row to get the
     * rotated CW shape. Prevent rotations if the piece goes out of bounds.
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

        if ((coorX + 35*(tran[0].length) > 350) || (35*coorY + 35*(tran.length) > 560)) {
            return;
        }
        coords = tran;
    }

    /* Gets the transpose of the current shape, then reverse the elements in the column to get the
     * rotated CCW shape. Prevent rotations if the piece goes out of bounds.
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

        if ((coorX + 35*(tran[0].length) > 350) || (35*coorY + 35*(tran.length) > 560)) {
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
     * Shift the X coordinates by deltaX only if no collision. Every second, the piece will shift down one block.
     */
    public void update() {
        long current = System.currentTimeMillis();

        if (collision == true) {
        }

        if (!(35*coorY + 35 + 35*(coords.length) > 560)) {
            if (current - lastTime > CURRENTSPEED) {
                coorY++;
                lastTime = System.currentTimeMillis();
            }
        } else {
            this.collision = true;
        }

        if (!(coorX + deltaX + 35 * (coords[0].length) > 350) && !(coorX + deltaX < 0)) {
            coorX += deltaX;
        }

        this.deltaX = 0; // return it back to 0
    }

    /* Pressing > or < will make the entire piece shift by deltaX */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /* When pressing down the timer's drop rate will increase */
    public void setCurrentSpeed() {
        CURRENTSPEED = 300;
    }

    /* When the down key is released the drop rate will reset */
    public void resetCurrentSpeed() {
        CURRENTSPEED = NORMALSPEED;
    }

    /*
     * Every time there is a 1 in the dimension for L, fill it in with the piece's default color
     * and draw the rectangular borders. The order of the code matters in setting the colors.
     * Set the color first and then make what the color is for.
     */
    public abstract void render(Graphics g);

}
