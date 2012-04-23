package org.mhoffman.life;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * These tests are here instead of the Scala test because ScalaTest wasn't letting me access protected methods.
 * I think I could with a more Junit-compatible testing mode, but this was faster.
 *
 */
public class LifeBoardTest {

    int[][] array1 = createArray1();
    int[][] array2 = createArray2();
    LifeBoard board;

    @Before
    public void setUp() throws Exception {
        board = new LifeBoard(array1);
    }

    @Test
    public void testNeighbors_top_left_corner() throws Exception {
        int[] neighbors = board.extractNeighbors(array1, 0,0);
        System.out.println("got back "+ Arrays.toString(neighbors));
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, neighbors);
    }


    @Test
    public void testNeighbors_top_right_corner() throws Exception {
        int[] neighbors = board.extractNeighbors(array2, 0,3);
        System.out.println("got back "+ Arrays.toString(neighbors));
        Arrays.sort(neighbors);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 3, 7,8}, neighbors);
//        assertArrayEquals(new int[]{0, 0, 0, 2, 0, 5, 6, 0}, neighbors);
    }


    @Test
    public void testNeighbors_top_middle() throws Exception {
        int[] neighbors = board.extractNeighbors(array2, 0,1);
        System.out.println("got back "+ Arrays.toString(neighbors));
        Arrays.sort(neighbors);
        assertArrayEquals(new int[]{0, 0, 0, 1, 3, 5, 6, 7}, neighbors);
    }


    @Test
    public void testNeighbors_bottom_right_corner() throws Exception {
        int[] neighbors = board.extractNeighbors(array2, 2,3);
        System.out.println("got back "+ Arrays.toString(neighbors));
        Arrays.sort(neighbors);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 7, 8, 11}, neighbors);
    }


    @Test
    public void testNeighbors_middle() throws Exception {
        int[] neighbors = board.extractNeighbors(array2, 1,2);
        System.out.println("got back "+ Arrays.toString(neighbors));
        Arrays.sort(neighbors);
        assertArrayEquals(new int[]{2,3,4,6,8,10,11,12}, neighbors);
    }

    private int[][] createArray1() {
        return new int[][]{ {1,0,0,0},
                            {0,0,1,0},
                            {0,0,0,1},
                            {1,1,0,0}};
    }

    private int[][] createArray2() {
        return new int[][]{ {1,2,3,4},
                            {5,6,7,8},
                            {9,10,11,12}};
    }
}
