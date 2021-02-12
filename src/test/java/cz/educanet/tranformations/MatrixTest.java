package cz.educanet.tranformations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    private IMatrix a;
    private IMatrix b;
    private IMatrix c;
    private IMatrix d;
    private IMatrix empty;

    @Before
    public void setUp() throws Exception {
        double[][] rawA = {
                {1, 1, 1},
                {1, 1, 1},
        };
        a = MatrixFactory.create(rawA);

        double[][] rawB = {
                {1, 1, 1},
                {1, 1, 1},
                {0, 0, 0},
        };
        b = MatrixFactory.create(rawB);
        double[][] rawC = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1},
        };
        c = MatrixFactory.create(rawC);

        double[][] rawEmpty = {};
        empty = MatrixFactory.create(rawEmpty);

        double[][] rawD = {
                {1, 1}
        };
        d = MatrixFactory.create(rawD);
    }

    @Test
    public void getRows() {
        assertEquals(2, a.getRows());
        assertEquals(3, b.getRows());
        assertEquals(3, c.getRows());
        assertEquals(0, empty.getRows());
    }

    @Test
    public void getColumns() {
        assertEquals(3, a.getColumns());
        assertEquals(3, b.getColumns());
        assertEquals(3, c.getColumns());
        assertEquals(0, empty.getColumns());
        assertEquals(2, d.getColumns());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void add() {
        assertEquals(2, a.add(b).get(0,1 ), 0.1);
        assertEquals(2, a.add(d).get(0,1 ), 0.1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void get() {
        assertEquals(1, a.get(0,0),0.1);
        assertEquals(1, a.get(0,20),0.1);
    }

    @Test
    public void times() {
        assertEquals(15, a.times(15).get(0,0), 0.1);
    }

    @Test(expected = ArithmeticException.class)
    public void timesScalar() {
        assertEquals(0, a.times(b).get(0,0), 0.1);
        assertEquals(1, b.times(c).get(0,0), 0.1);
    }


    @Test
    public void transpose() {
    }

    @Test
    public void determinant() {
    }
}
