package org.mhoffman.life;

import com.avaje.ebean.config.MatchingNamingConvention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    private static final Logger log = LoggerFactory.getLogger(LifeBoard.class);

    // normally, i'm against arrays instead of ArrayLists; after all, ArrayList gives you expansion for free...
    // but in this case we want the board to be restricted to a certain size, and we can easily predict when it
    // needs to expand...and we certainly want every row and every column to be of equal length. So 2d array it is.
    final private int[][] cells;
    final int rows;
    final int cols;

    public LifeBoard(int[][] array) {
        this.cells = array;
        rows = cells.length;
        cols = getColumns(cells);
    }


    public LifeBoard evolve() {
        // for each cell,
        //    extract its neighbors
        //    figure out what its state
        //    add that state to the new board

        // note that right now the board stays the same size, and edges are treated as "dead".  That could easily change.
        // we could just plug in alternate rules here.
        final int[][] newBoard = createNewBoard();
        final int newRows = newBoard.length;
        final int newCols = getColumns(newBoard);
        for (int row = 0; row < newRows; row++) {
            for (int col = 0; col < newCols; col++) {
                int[] neighbors = extractNeighbors(cells, row, col);
                newBoard[row][col] = evolveCell(cells[row][col], neighbors);
            }
        }
        return new LifeBoard(newBoard);
    }

    /**
     * This is a memory-inefficient algorithm for extracting neighbors because we're creating a 2 temporary hashsets per invocation.
     * So if memory usage becomes a problem, this might be a place to optimize.
     * @param cells not empty nor null
     * @param row the x coordinate to find neighbors for -- assumed valid!
     * @param col the y coordinate to find neighbors for -- assumed valid!
     * @return an array with neighbors. May or may not have a full 8 elements.
     */
    protected int[] extractNeighbors(int[][] cells, int row, int col) {
//        ArrayList<Integer> neighbors = new ArrayList<Integer>(8);
        int[] neighbors = new int[8];
        Arrays.fill(neighbors, 0);
        // i'm sure there's a very clever algorithm for this....

        int maxRow = cells.length;
        int maxCol = getColumns(cells);
        int c = 0;
        for (int newCol = col -1; newCol <= col+1; newCol++) {
            for (int newRow = row-1; newRow <= row+1; newRow++) {
                if (newRow == row && newCol == col) {
                    //this is the cell itself; it is not its own neighbor.
                } else {
                    neighbors[c] = getCell(cells, newRow, newCol, maxRow, maxCol);
                    c++;
                }
            }
        }
        return neighbors;
    }

    private int getCell(int[][] cells, int row, int col, int maxRows, int maxCols) {
        if (row >= maxRows || col >= maxCols || row < 0 || col < 0) {
            // if we're trying to get a cell that's off the edge of our array, return 0.
            return 0;
        } else {
            return cells[row][col];
        }
    }

    private String arrayToString(int[][] cells) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : cells) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public static int getRows(int[][] cells) {
        return cells.length;
    }

    public static int getColumns(int[][] cells) {
        int maxCols;
        if (cells.length > 0) maxCols = cells[0].length;
        else maxCols = 0;
        return maxCols;
    }

    /**
     * override this if you want the board to expand every time
     *
     * @return
     */
    public int[][] createNewBoard() {
        return new int[rows][cols];
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

    protected int countLiveCells(int[] neighbors) {
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
