package Block;

import Game.Board;
import Game.Tetromino;
import Game.Colors;

import java.awt.*;

public class I extends Shape{

    Tetromino shape;
    Board board;
    private Color color;
    private int colorNum;

    /* I Shape
     * [1 1 1]
     */

    public I(Board b) {
        this.board = b;
        shape = Tetromino.I;
        this.color = Colors.PINK.getColor();
        this.colorNum = Colors.PINK.getColorNum();
        this.coords = new int[][] {{1,1,1}};
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
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(3));
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
