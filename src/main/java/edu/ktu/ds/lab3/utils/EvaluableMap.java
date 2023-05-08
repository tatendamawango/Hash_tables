package edu.ktu.ds.lab3.utils;

import java.util.ArrayList;

/**
 * The interface describes the calculation of the hash table characteristics
 * Image ADT
 *
 * @param <K> Key
 * @param <V> Value
 */
public interface EvaluableMap<K, V> extends Map<K, V> {

    /**
     * Returns the maximum chain length.
     *
     * @return Maximum chain length.
     */
    default int getMaxChainSize(){
        return -1;
    }

    /**
     * Returns the hash table for the amount of remixes that occurred.
     *
     * @return Amount of mixing.
     */
    int getRehashesCounter();

    /**
     * Returns table capacity.
     *
     * @return Table capacity.
     */
    int getTableCapacity();

    /**
     * Returns the index of the last element in the hash table array.
     *
     * @return The index of the last element in the hash table array.
     */
    int getLastUpdated();

    /**
     * Returns the number of elements occupied by an hash table array.
     *
     * @return the number of elements occupied by the hash table array.
     */
    int getNumberOfOccupied();

    int getNumberOfCollisions();

    ArrayList getNumberCollisions();
}
