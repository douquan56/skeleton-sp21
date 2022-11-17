package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> expected = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        expected.addLast(4);
        buggy.addLast(4);
        expected.addLast(5);
        buggy.addLast(5);
        expected.addLast(6);
        buggy.addLast(6);

        assertEquals(expected.size(), buggy.size());
        assertEquals(expected.removeLast(), buggy.removeLast());
        assertEquals(expected.removeLast(), buggy.removeLast());
        assertEquals(expected.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> expected = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                expected.addLast(randVal);
                buggy.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(expected.size(), buggy.size());
            } else if (operationNumber == 2) {
                if (expected.size() > 0) {
                    assertEquals(expected.getLast(), buggy.getLast());
                }
            } else if (operationNumber == 3) {
                if (expected.size() > 0) {
                    assertEquals(expected.removeLast(), buggy.removeLast());
                }
            }
        }
    }
}
