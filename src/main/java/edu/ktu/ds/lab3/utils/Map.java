package edu.ktu.ds.lab3.utils;

import java.util.List;
import java.util.Set;

/**
 * The interface describes the Image ADT.
 *
 * @param <K> Image pair key
 * @param <V> The meaning of the image pair
 */
public interface Map<K, V> {

    /**
     * Checking if the image is empty.
     *
     * @return true if empty
     */
    boolean isEmpty();

    /**
     * Returns the number of pairs in the image.
     *
     * @return Returns the number of pairs in the image.
     */
    int size();

    /**
     * The image is being cleared.
     */
    void clear();

    /**
     * A new pair is added to the image.
     *
     * @param key   key,
     * @param value value.
     * @return Returns the value of the image pair.
     */
    V put(K key, V value);

    /**
     * Returns the value of the image pair.
     *
     * @param key key.
     * @return Returns the value of the image pair.
     */
    V get(K key);

    /**
     *The pair is removed from the image.
     *
     * @param key key.
     * @return Returns the deleted image pair value.
     */
    V remove(K key);

    /**
     * Checks if there is a key with the key in the image.
     *
     * @param key key.
     * @return true if there is a key pair in the image, false otherwise
     */
    boolean contains(K key);

    /**
     * Replaces the existing key in the image and its corresponding value with a new value and returns true.
     * If the key does not exist in the image or its value does not match the old value specified in the method argument,
     * the change is not performed and is returned as false.
     *
     * @param key      key.
     * @param oldValue old meaning.
     * @param newValue new meaning.
     * @return true if a change has occurred
     */
    boolean replace(K key, V oldValue, V newValue);

    /**
     * Checks whether one or more keys for the value specified by the method argument exist in the image
     *
     * @param value value.
     * @return true if one or more keys for the value specified by the method argument exist in the image
     */
    boolean containsValue(Object value);

    Set<K> keySet();

    List<V> values();



}
