/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.utils;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Darius
 */
public class HashMapOa<K, V> implements EvaluableMap<K, V> {

    public enum OpenAddressingType {

        LINEAR,
        QUADRATIC,
        DOUBLE_HASHING
    }

    public static final int DEFAULT_INITIAL_CAPACITY = 8;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final HashManager.HashType DEFAULT_HASH_TYPE = HashManager.HashType.DIVISION;
    public static final OpenAddressingType DEFAULT_OPEN_ADDRESSING_TYPE = OpenAddressingType.LINEAR;

    // Hash table
    protected Entry<K, V>[] table;
    protected Entry<K,V> dummy = new Entry<K,V>();
    // The number of key-value pairs in the table
    protected int size = 0;
    // Load factor
    protected float loadFactor;
    // Hash method
    protected HashManager.HashType ht;
    //--------------------------------------------------------------------------
    //  Hash table evaluation parameters
    //--------------------------------------------------------------------------
    // Amount of mixing
    protected int rehashesCounter = 0;
    // The index of the last element of the added array in the hash table
    protected int lastUpdated = 0;
    // Number of items occupied in the table
    protected int numberOfOccupied = 0;

    private final Entry<K, V> DELETED = new Entry<>();
    private final OpenAddressingType oaType;

    public HashMapOa() {this(DEFAULT_HASH_TYPE);
    }

    public HashMapOa(HashManager.HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, ht);
    }

    public HashMapOa(int initialCapacity, HashManager.HashType ht) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, ht, DEFAULT_OPEN_ADDRESSING_TYPE);
    }

    public HashMapOa(float loadFactor, HashManager.HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor, ht, DEFAULT_OPEN_ADDRESSING_TYPE);
    }

    public HashMapOa(int initialCapacity, float loadFactor, HashManager.HashType ht, OpenAddressingType oaType) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }

        if ((loadFactor <= 0.0) || (loadFactor > 1.0)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.table = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
        this.oaType = oaType;
    }

    /**
     * Checking if the image is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of pairs in the image.
     *
     * @return Returns the number of pairs in the image.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The image is being cleared.
     */
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        lastUpdated = 0;
        rehashesCounter = 0;
        numberOfOccupied = 0;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in contains(K key)");
        }

        return get(key) != null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null in put(K key, V value)");
        }
        int position = findPosition(key, true);
        if (position == -1) {
            rehash();
            return put(key, value);
        }

        if (table[position] == null || DELETED.equals(table[position])) {
            table[position] = new Entry(key, value);
            size++;

            if (size > table.length * loadFactor) {
                rehash();
            } else {
                numberOfOccupied++;
                lastUpdated = position;
            }
        } else {
            table[position].value = value;
            lastUpdated = position;
        }

        return value;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(K key)");
        }

        int position = findPosition(key, false);
        if (position != -1 && table[position] != null && table[position] != DELETED) {
            return table[position].value;
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = findPosition(key, false);

        while (table[index] != null) {
            if (table[index].key == key) {
                Entry<K,V> temp = table[index];
                table[index] = dummy;
                size--;
                return temp.value;
            }
            index--;
            index %= getTableCapacity();
        }

        return null;
    }

    @Override
    public String toString() {
        return Arrays.stream(table)
                .filter(entry -> entry != null && !DELETED.equals(entry))
                .map(Entry::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public int getNumberOfCollisions() {return 0;}

    @Override
    public ArrayList getNumberCollisions() {return null;}

    @Override
    public List<V> values() {
        return null;
    }

    private void rehash() {
        HashMapOa<K, V> newMap = new HashMapOa<>(table.length * 2, loadFactor, ht, oaType);
        Arrays.stream(table).filter(Objects::nonNull).forEach(kvEntry -> newMap.put(kvEntry.key, kvEntry.value));
        table = newMap.table;
        numberOfOccupied = newMap.numberOfOccupied;
        lastUpdated = newMap.lastUpdated;
        rehashesCounter++;
    }

    private int findPosition(K key, boolean stopAtDeleted) {
        int index = HashManager.hash(key.hashCode(), table.length, ht);

        int position = index;
        for (int i = 0; i < table.length; i++) {
            if (table[position] == null) {
                return position;
            }
            if (DELETED.equals(table[position]) && stopAtDeleted) {
                return position;
            }
            if (!DELETED.equals(table[position]) && table[position].key.equals(key)) {
                return position;
            }

            position = calculatePosition(index, i, key);
        }
        return -1;
    }

    private int calculatePosition(int index, int i, K key) {
        switch (oaType) {
            case LINEAR:
                return (index + i + 1) % table.length;
            case QUADRATIC:
                return (index + (i + 1) * (i + 1)) % table.length;
            case DOUBLE_HASHING:
                return (index + i * (7 - Math.abs(key.hashCode()) % 7)) % table.length;
        }
        return index;
    }

    /**
     * Returns the amount of remixes that occurred during the formation of the hash table.
     *
     * @return Amount of mixing.
     */
    @Override
    public int getRehashesCounter() {
        return rehashesCounter;
    }

    /**
     * Returns the capacity of the hash table.
     *
     * @return Hash table capacity.
     */
    @Override
    public int getTableCapacity() {
        return table.length;
    }

    /**
     * Returns the index of the last element in the hash table array.
     *
     * @return The index of the last element in the hash table array.
     */
    @Override
    public int getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Returns the number of elements in the hash table array occupied.
     *
     * @return The number of elements in the hash table array occupied
     */
    @Override
    public int getNumberOfOccupied() {
        return numberOfOccupied;
    }

    public boolean replace(K key, V oldValue, V newValue) {
        for (Entry<K,V> node: table) {
            if (node != null) {
                if (node.key==key && node.value==oldValue) {
                    node.value=newValue;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsValue(Object value) {
        V look = (V) value;
        for (Entry<K,V> node: table) {
            if (node != null) {
                if (node.value == look) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static class Entry<K, V> {

        // Key
        protected K key;
        // Value
        protected V value;

        protected Entry() {
        }

        protected Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
