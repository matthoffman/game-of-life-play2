package org.mhoffman.life;

/**
 * Represents a board for the game of life.
 * This object is immutable; it will return a new instance of LifeBoard on each call to {@link #evolve()}.
 * <p/>
 * Eventually, if we find ourselves wanting multiple implementations (perhaps different rule variations?) we will want
 * to change this to an interface. But that's not necessary yet.
 * <p/>
 * Note that this treats cells as ints, instead of booleans. Since cells are either alive or dead, I certainly could
 * have treated them as booleans. But I decided to go int instead, simply because there are some interesting variations
 * of Conway's original rules that involve multiple colors. This is probably a case where I should stick to YAGNI and
 * use booleans, but it was just as easy. If I need to optimize for very, very large boards later, I can switch back to
 * booleans.
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
        // for each cell,
        //    extract its neighbors

        return new LifeBoard(new int[1][1]);//TODO: implement me
    }

    /**
     * Return the new state of the this cell, given the state of its neighbors
     * <p/>
     * The code should take this board and create a new board for the next
     * generation based on the following rules:
     * 1) Any live cell with fewer than two live neighbours dies (underpopulation)
     * 2) Any live cell with two or three live neighbours lives on to the
     * next generation (survival)
     * 3) Any live cell with more than three live neighbours dies
     * (overcrowding)
     * 4) Any dead cell with exactly three live neighbours becomes a live
     * cell (reproduction)
     *
     * @return
     */
    public int evolveCell(int cell, int[] neighbors) {
        // note that we're not checking how many neighbors we have. That way, we can choose to call this for cells along
        // the edges, if we decide to treat the edges as automatically "dead".
        int numberOfLiveNeighbors = countLiveCells(neighbors);
        if (numberOfLiveNeighbors > 3) return 0; // died of overcrowding
        if (cell == 0 && numberOfLiveNeighbors == 3) return 1; // reproduction!
        if (cell == 1 && numberOfLiveNeighbors < 2) return 0; // underpopulation
        return cell;// stay as it is.
    }

    private int countLiveCells(int[] neighbors) {
        int sum = 0;
        for (int neighbor : neighbors) {
            if (neighbor == 1) sum++;
        }
        return sum;
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
