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
    private int[][] gameBoard = new int[boardHEIGHT][boardWIDTH];
    private boolean gameover;
    private Timer timer;
    private Shape currentShape;

    public Board() {
        setFocusable(true); // Make the gameboard the focus of the GUI
        initKeyListener();
        this.gameover = false;
        setCurrentPiece();

        timer = new Timer(refresh, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGameOver();
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

        for (int i = 0; i < boardHEIGHT; i++) { // horizontal line
            g.drawLine(0, i * 35, 35 * boardWIDTH, i * 35); //35 is temp
        }

        for (int j = 0; j < boardWIDTH; j++) { // vertical line
            g.drawLine(j * 35, 0, j * 35, 35 * boardHEIGHT);
        }
        currentShape.render(g);

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] != 0) {
                    switch (gameBoard[i][j]) {
                        case 1:
                            g.setColor(Colors.PINK.getColor());
                            break;
                        case 2:
                            g.setColor(Colors.CYAN.getColor());
                            break;
                        case 3:
                            g.setColor(Colors.GREEN.getColor());
                            break;
                        case 4:
                            g.setColor(Colors.BLUE.getColor());
                            break;
                        case 5:
                            g.setColor(Colors.RED.getColor());
                            break;
                        case 6:
                            g.setColor(Colors.MAGENTA.getColor());
                            break;
                        case 7:
                            g.setColor(Colors.ORANGE.getColor());
                            break;
                    }
                    g.fillRect((j*35),(i*35),35,35);
                    g.setColor(Colors.BLACK.getColor());
                    g.drawRect((j*35),(i*35),35,35);
                }
            }
        }


    }

    /* Creates and set a new Shape piece to be used when it's in focus.
    *  At the same time, check if it's legal to be placed.
    *  If it's illegal then game over has occurred.*/
    public void setCurrentPiece() {
        Shape newPiece;
        int random = (int) (Math.random() * 7); // 0 to 6
        switch (random) {
            case 0:
                newPiece = new I(this);
                break;
            case 1:
                newPiece = new J(this);
                break;
            case 2:
                newPiece = new L(this);
                break;
            case 3:
                newPiece = new O(this);
                break;
            case 4:
                newPiece = new S(this);
                break;
            case 5:
                newPiece = new T(this);
                break;
            default:
                newPiece = new Z(this);
                break;
        }
        currentShape = newPiece;

        int coorX = currentShape.getCoorX();
        for (int i=0; i<currentShape.coords.length; i++) {
            for (int j=0; j<currentShape.coords[i].length; j++) {
                if (gameBoard[i][j+(coorX/35)] != 0) {
                    gameover = true;
                }
            }
        }
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
                    case (KeyEvent.VK_SPACE): //fix this
                        currentShape.setMaxSpeed();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_DOWN):
                        currentShape.resetCurrentSpeed();
                        break;
                    case (KeyEvent.VK_SPACE):
                        currentShape.resetCurrentSpeed();
                        break;
                }
            }

            public void keyTyped(KeyEvent e) {}
        });
    }

    private void checkGameOver() {
        currentShape.update();
        if (gameover == true) {
            timer.stop();
        }
    }

    public Board getGameBoard() {
        return this;
    }

    public int[][] getGameBoardArray() {
        return gameBoard;
    }

}
