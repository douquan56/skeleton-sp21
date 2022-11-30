package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


public class MaxArrayDequeTest {

    public static class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    public static class StringLengthComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    public static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1 - i2;
        }
    }

    @Test
    public void intMaxTest() {

        Comparator<Integer> ic = new IntegerComparator();
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<Integer>(ic);
		// should return null
        assertNull(mad1.max());

		mad1.addFirst(1);
        mad1.addFirst(2);
        mad1.addFirst(3);
        mad1.addFirst(4);
        assertEquals("Max value should be ", 4, (double) mad1.max(), 0.0);

		mad1.removeFirst();
        assertEquals("Max value should be ", 3, (double) mad1.max(), 0.0);

        mad1.removeLast();
        assertEquals("Max value should be ", 3, (double) mad1.max(), 0.0);
    }

    @Test
    public void strMaxTest() {

        Comparator<String> sc = new StringComparator();
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<String>(sc);
        // should return null
        assertNull(mad1.max());

        mad1.addFirst("Hello");
        mad1.addFirst("World");
        assertEquals("Max value should be ", "World", mad1.max());

        mad1.removeFirst();
        assertEquals("Max value should be ", "Hello", mad1.max());
    }

    @Test
    public void strLenMaxTest() {

        Comparator<String> sc = new StringLengthComparator();
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<String>(sc);
        // should return null
        assertNull(mad1.max());

        mad1.addLast("Hello");
        mad1.addLast("World");
        mad1.addLast("!");
        assertNotEquals("Max value should not be ", "!", mad1.max());

        mad1.removeFirst();
        assertEquals("Max value should be ", "World", mad1.max());

        mad1.removeFirst();
        assertEquals("Max value should be ", "!", mad1.max());
    }

}
