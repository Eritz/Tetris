package Game;

import Block.*;
import Block.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener{

    final int[][] gameBoard = new int[16][10];
    boolean gameover;

    public Board() {
        createBoard();
    }


    /*   {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0}}
    */
    private void createBoard() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }
    // Temporary
    private void startGame() {
        while (!gameover) {
            Shape Piece;
            int choice = (int) (Math.random()*4);
            /*
            for (int i = 0; i < Piece.dim.length; i++) {
                for (int j = 0; j<Piece.dim.length; j++) {
                    gameBoard[i][j] = Piece[i][j];
                }
            }*/
        };
    }

    // getPiece function
    /* Creates a block to be used when it's in focus.*/
    private Shape getPiece() {
        // Switch case this
        Shape newPiece = new L(4,5);
        return newPiece;
    }

    // keyPressed Function
    /* When you press <, >, SPACE, Z, X it'll trigger the KeyEvent
     * Then it'll go to the Block and do its function
     * Afterwards, it should update the Block's dimension to its new corresponding location on the board
     * Every time a key is triggered, it should check if it's valid before proceeding
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_Z):
                // CCW
                break;
            case (KeyEvent.VK_X) :
                // CW
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    // isValid function
    /* Check if the shape is within bounds
     * It cannot go past the left of the row. so make sure that [i,0] and [i,10] are the farthest
     * It cannot bypass an already set block that has landed; i.e, if there is no other shape under it, can land
     */

    // isLine function
    /* Check if a line has been made from [15,0] to [15,9] */

    // removeLine function
    /* while isLine() returns true then clear the line
     * then everything above it is also pushed down
     * call this function after every piece lands
     */


}
