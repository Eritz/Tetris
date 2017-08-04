package Block;

import Game.Board;
import Game.Tetromino;

import java.awt.*;

public class O extends Shape {

    Tetromino shape;
    private Board gameboard;
    private Color color;

    /* O shape
     * [1 1]
     * [1 1]
     */

    public O(Board b) {
        shape = Tetromino.L;
        this.gameboard = b;

        this.color = Color.RED;
        this.coords = new int[][]{ {1,1}, {1,1} };

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