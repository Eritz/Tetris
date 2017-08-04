package Block;

import Game.Board;
import Game.Tetromino;

import java.awt.*;

public class I extends Shape{

    Tetromino shape;
    private Board gameboard;
    private Color color;

    /* I Shape
     * [1 1 1]
     */

    public I(Board b) {
        shape = Tetromino.S;
        this.gameboard = b;

        this.color = Color.PINK;
        this.coords = new int[][] {{1,1,1}};

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
}
