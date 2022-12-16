package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BST {
        private Node root;

        private class Node {
            K key;
            V value;
            Node left;
            Node right;
            int size;

            Node(K key, V value, int size) {
                this.key = key;
                this.value = value;
                this.size = size;
            }
        }

        BST() { }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }
            return node.size;
        }

        public boolean contains(K key) {
            return contains(root, key);
        }

        private boolean contains(Node node, K key) {
            if (node == null) {
                return false;
            }
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                return contains(node.left, key);
            } else if (cmp > 0) {
                return contains(node.right, key);
            } else {
                return true;
            }
        }

        public V get(K key) {
            return get(root, key);
        }

        private V get(Node node, K key) {
            if (node == null) {
                return null;
            }
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                return get(node.left, key);
            } else if (cmp > 0) {
                return get(node.right, key);
            } else {
                return node.value;
            }
        }

        public void put(K key, V value) {
            root = put(root, key, value);
        }

        private Node put(Node node, K key, V value) {
            if (node == null) {
                return new Node(key, value, 1);
            }
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = put(node.left, key, value);
            } else if (cmp > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        }

        public void delete(K key) {
            root = delete(root, key);
        }

        private Node delete(Node node, K key) {
            if (node == null) {
                return null;
            }
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = delete(node.left, key);
            } else if (cmp > 0) {
                node.right = delete(node.right, key);
            } else {
                if (node.right == null) {
                    return node.left;
                }
                if (node.left == null) {
                    return node.right;
                }
                Node tmp = node;
                node = min(tmp.right);
                node.right = deleteMin(tmp.right);
                node.left = tmp.left;
            }
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        }

        private Node min(Node node) {
            if (node.left == null) {
                return node;
            }
            return min(node.left);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) {
                return node.right;
            }
            node.left = deleteMin(node.left);
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        }

        public void printInOrder() {
            printInOrder(root);
        }

        private void printInOrder(Node node) {
            if (node == null) {
                return;
            }
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }

    private BST bst;

    public BSTMap() {
        this.bst = new BST();
    }

    @Override
    public void clear() {
        this.bst = new BST();
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (bst == null) {
            return false;
        }
        return bst.contains(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key) {
        if (bst == null) {
            return null;
        }
        return bst.get(key);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return bst.size();
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        bst.put(key, value);
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (bst == null) {
            return null;
        }
        if (bst.contains(key)) {
            V value = bst.get(key);
            bst.delete(key);
            return value;
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException. */
    @Override
    public V remove(K key, V value) {
        if (bst == null) {
            return null;
        }
        if (bst.contains(key)) {
            if (bst.get(key) == value) {
                bst.delete(key);
                return value;
            }
        }
        return null;
    }

    /* Returns an iterator that iterates over the keys of the dictionary.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        bst.printInOrder();
    }
}
