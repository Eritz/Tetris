package Block;
/*http://tetris.wikia.com/wiki/SRS*/

public abstract class Shape {

    public int b_x,b_y;
    public int[][] dim;
    int[][] mirror;
    private boolean landed; // false would be the focus, true is when it's landed
    // make "game" part of the shape constructor args

    /**
     * @param b_x the x-location on the board (temp)
     * @param b_y the y-location on the board (temp)
     */

    public Shape(int b_x, int b_y) {
        int[][] coord = new int[3][3];
        int[][] mir = new int[3][3];

        this.b_x = b_x;
        this.b_y = b_y;
        this.dim = coord;

        // Identity matrice
        for (int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if (i==j) {
                    mir[i][j] = 1;
                } else {
                    mir[i][j] = 0;
                }
            }
        }
        // Mirror the identity matrice
        for (int i =0; i<3; i++){
            for (int j=0; j<1; j++) {
                int temp = mir[i][j]; // temporarily store the original value
                mir[i][j] = mir[i][mir[i].length - j - 1]; // the 1 is to stop the middle
                mir[i][mir[i].length - j - 1] = temp;
            }
        }

        this.mirror = mir;

    }

    public abstract void rotateClockWise();

    public abstract void rotateCounterClockWise();

    public abstract void setTranspose();

    public abstract int[][] getTranspose();

}
