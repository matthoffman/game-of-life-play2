package org.mhoffman.life;

/**
 * Represents a board for the game of life.
 * This object is immutable; it will return a
 * <p/>
 * Eventually, if we find ourselves wanting multiple implementations (perhaps different rule variations?) we will want
 * to change this to an interface. But that's not necessary yet.
 */
public class LifeBoard {
    // normally, i'm against arrays instead of ArrayLists; after all, ArrayList gives you expansion for free...
    // but in this case we want the board to be restricted to a certain size, and we can easily predict when it
    // needs to expand...and we certainly want every row and every column to be of equal length. So 2d array it is.
    private int[][] cells;

    public LifeBoard(int[][] array) {
        this.cells = array;
    }


    public LifeBoard evolve() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * DO NOT MODIFY THE RESULTING ARRAY!  It may or may not be defensively copied; assume that modifications will
     * cause immediate harm to innocent kittens.
     *
     * @return
     */
    public int[][] asArray() {
        return cells; // note that we're not preventing cells from being modified. We could defensively clone if we feel it necessary...
    }
}
