package Block;

import Game.Board;
import Game.Colors;

import java.awt.*;

public abstract class Shape {

    Color borders = Colors.BLACK.getColor();
    int coorX,coorY;
    long NORMALSPEED = 1000, CURRENTSPEED; //1000ms
    long lastTime;

    private int[][] transpose;
    public int[][] coords;
    private int deltaX;
    private boolean collision;
    private boolean collisionX;



    public Shape() {
        coorX = 175; // just initial displacement
        coorY = 0;
        CURRENTSPEED = NORMALSPEED;
        this.lastTime = System.currentTimeMillis();

    }

    /*
     * Every time there is a 1 in the dimension for L, fill it in with the piece's default color
     * and draw the rectangular borders. The order of the code matters in setting the colors.
     * Set the color first and then make what the color is for.
     */
    public abstract void render(Graphics g);

    public abstract int getShapeColorNum();

    public abstract Board getCurrentBoard();

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

    /* Clear the Line starting from the bottom to the top.
     * If the row is not filled, focus on the above row.
     * Then when the row is filled, the current row (set by the row--) is given the row above it .*/
    public void checkLine() {
        Board board = getCurrentBoard();
        int row = board.getGameBoardArray().length - 1; // -1 because of array format

        for (int i=row; i > 0; i--) {
            int count = 0;
            for (int j=0; j < board.getGameBoardArray()[0].length; j++) {
                if (board.getGameBoardArray()[i][j] != 0) {
                    count++;
                }
                board.getGameBoardArray()[row][j] = board.getGameBoardArray()[i][j]; // get the top row and put it down
            }
            if (count < board.getGameBoardArray()[0].length) { // let the loop focus only on rows with filled units
                row--;
            } else {
                int score = board.getGameBoardArray().length - row;
                board.updateScore(score);
            }

        }
    }

    /* This method is called by the board's timer. It will run this method and repaint the screen.
     * Shift the X coordinates by deltaX only if no collision. Every second, the piece will shift down one block.
     */
    public void update() {
        long current = System.currentTimeMillis();
        Board board = getCurrentBoard();

        /* When there is a collision, call the gameboard to make a new piece.
         * Also, set the gameboard array with the previous piece's coordinates.
         * Afterwards, the gameboard will set a new piece.
         */
        if (this.collision == true) {
            for (int i=0; i<coords.length; i++) {
                for (int j=0; j<coords[i].length; j++) {
                    if (coords[i][j] != 0) { //if the current shape coords have 1
                        board.getGameBoardArray()[i+coorY][j+(coorX/35)] = getShapeColorNum();
                    }
                }
            }
            checkLine();
            board.setCurrentPiece();
        }

        /* Move the Piece down and prevent it from going out of bounds, and check
         * if there is already a piece such that it can't fall.
         * If there is, set the collision to true.
         */
        if (!(35*coorY + 35 + 35*(coords.length) > 560)) {
            for (int i=0; i<coords.length; i++) {
                for (int j = 0; j < coords[i].length; j++) {
                    if (coords[i][j] != 0) { //if there is another piece that occupies the current shape's 1
                        if (board.getGameBoardArray()[i+coorY+1][j+(coorX/35)] != 0) {
                            this.collision = true;
                        }
                    }
                }
            }
            if (current - lastTime > CURRENTSPEED) {
                coorY++;
                lastTime = System.currentTimeMillis();
            }

        } else { // When the piece reaches the floor
            this.collision = true;
        }

        /* Move the Piece left or right, and prevent it from going out of bounds, and
         * check for possible collisions on the piece's left and right side.
         * If there is, prevent the piece from clipping by collisionX variable.
         */
        if (!(coorX + deltaX + 35 * (coords[0].length) > 350) && !(coorX + deltaX < 0)) {
            for (int i = 0; i < coords.length; i++) {
                for (int j = 0; j < coords[i].length; j++) {
                    if (coords[i][j] != 0) {
                        if (board.getGameBoardArray()[i+coorY][(deltaX / 35) + j + (coorX / 35)] != 0) {
                            collisionX = true;
                        }
                    }
                }
            }
            if (collisionX == false) {
                coorX += deltaX;
            }
        }

        this.deltaX = 0; // return it back to 0
        this.collisionX = false;
    }

    /* Pressing > or < will make the entire piece shift by deltaX */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /* When pressing down the timer's drop rate will increase */
    public void setCurrentSpeed() {
        CURRENTSPEED = 100;
    }

    /* When the down key is released the drop rate will reset */
    public void resetCurrentSpeed() {
        CURRENTSPEED = NORMALSPEED;
    }

    public int getCoorX() {
        return coorX;
    }

    public int getCoorY() {
        return coorY;
    }


}
