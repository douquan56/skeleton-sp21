package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Quan Dou
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private static final int INITIAL_SIZE = 4;
    private static final double MAX_LOAD = 0.75;
    private int n;
    private int m;
    private double maxLoad;
    /** Constructors */
    public MyHashMap() {
        this(INITIAL_SIZE, MAX_LOAD);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, MAX_LOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        n = 0;
        m = initialSize;
        this.maxLoad = maxLoad;
        buckets = new Collection[initialSize];
        for (int i = 0; i < m; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return null;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return Math.floorMod(h, m);
    }

    @Override
    public void clear() {
        n = 0;
        m = INITIAL_SIZE;
        buckets = new Collection[INITIAL_SIZE];
        for (int i = 0; i < m; i++) {
            buckets[i] = createBucket();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void put(K key, V value) {
        int i = hash(key);
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        Node node = new Node(key, value);
        buckets[i].add(node);
        n++;

        double loadFactor = (double) n / m;
        if (loadFactor >= maxLoad) {
            resize();
        }
    }

    private void resize() {
        MyHashMap<K, V> temp = new MyHashMap<>(2 * m, maxLoad);
        for (int i = 0; i < m; i++) {
            for (Node node : buckets[i]) {
                temp.put(node.key, node.value);
            }
        }
        this.n = temp.n;
        this.m = temp.m;
        this.buckets = temp.buckets;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (Node node : buckets[i]) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIter();
    }

    private class MyHashMapIter implements Iterator<K> {
        private Iterator<K> keySetIter;

        public MyHashMapIter() {
            Set<K> keySet = keySet();
            keySetIter = keySet.iterator();
        }

        @Override
        public boolean hasNext() {
            return keySetIter.hasNext();
        }

        @Override
        public K next() {
            return keySetIter.next();
        }
    }
}
