package edu.ktu.ds.lab3.utils;

/**
 * @param <K>
 * @param <V>
 */
public interface ParsableMap<K, V> extends EvaluableMap<K, V> {

    V put(String key, String value);

    void load(String filePath);

    void save(String filePath);

    void println(String delimiter);

    /**
     * Returns formatted hash table contents for display. Ex:
     * [0] -> Pair1 -> Pair3
     * [1] -> Pair4 -> Pair2
     * ...
     *
     * @return Returns the contents of a hash table in a 2D array
     */
    String[][] getMapModel();
}
