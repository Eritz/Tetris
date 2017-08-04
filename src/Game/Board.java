package Game;

import Block.*;
import Block.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel {

    private final int boardHEIGHT = 16, boardWIDTH = 10;
    private final int refresh = 1000/60; // 1000ms divided by 60FPS
    public int[][] gameBoard = new int[boardHEIGHT][boardWIDTH];
    private boolean gameover;
    private Timer timer;
    private Shape currentShape;

    public Board() {
        setFocusable(true); // Make the gameboard the focus of the GUI
        initKeyListener();
        this.gameover = false;
        currentShape = new L(this);

        timer = new Timer(refresh, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentShape.update();
                repaint();
            }
        });
        timer.start();
    }

    /* This draws the lines on the board panel and the current shape.
     * Without the super, it would think to draw the entire frame again, but
     * with the super it would only focus on the game board when the
     * repaint method is called.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i=0; i<boardHEIGHT; i++) { // horizontal line
            g.drawLine(0, i*35,35*boardWIDTH,i*35); //35 is temp
        }

        for (int j =0; j<boardWIDTH; j++) { // vertical line
            g.drawLine(j*35, 0,j*35, 35*boardHEIGHT);
        }

        currentShape.render(g);

    }

    // getPiece function
    /* Creates a block to be used when it's in focus.*/
    private void setCurrentPiece() {
        // Switch case this
        Shape newPiece = new L(this);
        currentShape = newPiece;
    }

    // keyPressed Function
    /* When you press <, >, SPACE, Z, X it'll trigger the KeyEvent
     * Then it'll go to the Block and do its function
     * Afterwards, it should update the Block's dimension to its new corresponding location on the board
     * Every time a key is triggered, it should check if it's valid before proceeding
     */

    private void initKeyListener() {
        this.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_Z):
                        // CCW
                        currentShape.rotateCounterClockWise();
                        break;
                    case (KeyEvent.VK_X) :
                        // CW
                        currentShape.rotateClockWise();
                        break;
                    case (KeyEvent.VK_LEFT):
                        // Move left
                        currentShape.setDeltaX(-35);
                        break;
                    case (KeyEvent.VK_RIGHT):
                        // Move right
                        currentShape.setDeltaX(35);
                        break;
                    case (KeyEvent.VK_DOWN):
                        currentShape.setCurrentSpeed();
                        break;
                    case (KeyEvent.VK_SPACE):
                        // drop it instantly
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_DOWN):
                        currentShape.resetCurrentSpeed();
                        break;
                }
            }

            public void keyTyped(KeyEvent e) {}
        });
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
