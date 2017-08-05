package Block;

import Game.Board;
import Game.Colors;
import Game.Tetromino;

import java.awt.*;

public class T extends Shape{

    Tetromino shape;
    private Board board;
    private Color color;
    private int colorNum;

    /* T Shape
     * [1 1 1]
     * [0 1 0]
     */

    public T(Board b) {
        this.board = b;
        shape = Tetromino.T;
        this.color = Colors.MAGENTA.getColor();
        this.colorNum = Colors.MAGENTA.getColorNum();
        this.coords = new int[][] {{1,1,1},{0,1,0}};
    }

    public Board getCurrentBoard() {
        return board;
    }

    public void render(Graphics g) {
        for (int i=0; i< coords.length; i++) {
            for (int j=0; j <coords[i].length; j++) {
                if (coords[i][j] != 0) {
                    g.setColor(color);
                    g.fillRect(j*35 + coorX,i*35 + 35*coorY,35,35); //reverse the coordinates
                    g.setColor(borders);
                    g.drawRect(j*35 + coorX,i*35 + 35*coorY,35,35);

                }
            }
        }
    }

    public int getShapeColorNum() {
        return colorNum;
    }
}
