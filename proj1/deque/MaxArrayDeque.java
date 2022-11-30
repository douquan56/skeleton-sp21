package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultComparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        defaultComparator = c;
    }

    public T max() {
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int size = size();
        T maxItem = get(0);
        for (int i = 1; i < size; i++) {
            T item = get(i);
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
