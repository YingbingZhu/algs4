import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {
    private Percolation sy = new Percolation(4);

    @Test
    public void open() {
        sy.open(1, 1);
        assertEquals(sy.numberOfOpenSites(), 1);

        sy.open(1,2);
        assertEquals(sy.numberOfOpenSites(), 2);
    }

    @Test
    public void isFull() {
        sy.open(1,3);
        assertTrue(sy.isFull(1,3));
        assertFalse(sy.isFull(1,2));

        sy.open(3,3);
        assertFalse(sy.isFull(3,3));

        sy.open(2,3);
        assertTrue(sy.isFull(2,3));
        assertTrue(sy.isFull(3,3));
        assertFalse(sy.isFull(4,3));

        assertFalse(sy.isFull(4,1));
        assertFalse(sy.isFull(4,4));

    }

    @Test
    public void numberOfOpenSites() {
        sy.open(1,1);
        assertEquals(sy.numberOfOpenSites(), 1);

        sy.open(4,4);
        assertEquals(sy.numberOfOpenSites(), 2);

        sy.open(4,1);
        assertEquals(sy.numberOfOpenSites(), 3);
    }

    @Test
    public void isPercolate() {
        sy.open(1,1);
        assertFalse(sy.percolates());
        sy.open(2,1);
        sy.open(3,1);
        sy.open(4,1);
        assertTrue(sy.percolates());
    }
}
