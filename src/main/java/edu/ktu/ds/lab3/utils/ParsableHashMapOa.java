package edu.ktu.ds.lab3.utils;

import edu.ktu.ds.lab3.demo.CarsGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static edu.ktu.ds.lab3.gui.Table.ARROW;

public class ParsableHashMapOa<K, V extends Parsable<V>> extends HashMapOa<K, V> implements ParsableMap<K, V> {

    private final Function<String, K> keyCreateFunction;   // function for creating a unique key object
    private final Function<String, V> valueCreateFunction; // function for creating a base object of value

    /**
     * Constructor with function for creating basic key and value objects
     *
     * @param keyCreateFunction
     * @param valueCreateFunction
     * @param ht
     */
    public ParsableHashMapOa(Function<String, K> keyCreateFunction,
                             Function<String, V> valueCreateFunction,
                             HashManager.HashType ht,
                             OpenAddressingType oaType) {

        this(keyCreateFunction, valueCreateFunction, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, ht, oaType);
    }


    /**
     * Constructor with function for creating basic key and value objects
     *
     * @param keyCreateFunction
     * @param valueCreateFunction
     * @param initialCapacity
     * @param loadFactor
     * @param ht
     */
    public ParsableHashMapOa(Function<String, K> keyCreateFunction,
                             Function<String, V> valueCreateFunction,
                             int initialCapacity,
                             float loadFactor,
                             HashManager.HashType ht,
                             OpenAddressingType oaType) {

        super(initialCapacity, loadFactor, ht, oaType);
        this.keyCreateFunction = keyCreateFunction;
        this.valueCreateFunction = valueCreateFunction;
    }

    @Override
    public V put(String key, String value) {
        return super.put(
                create(keyCreateFunction, key, "Key generation function not set"),
                create(valueCreateFunction, value, "No value creation function set")
        );
    }

    /**
     * Forms an image from a filePath file
     *
     * @param filePath
     */
    @Override
    public void load(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        clear();
        try (BufferedReader fReader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            fReader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> put(CarsGenerator.generateId(), line));
        } catch (FileNotFoundException e) {
            Ks.ern("No valid data file found: " + e.getLocalizedMessage());
        } catch (IOException | UncheckedIOException e) {
            Ks.ern("File read error: " + e.getLocalizedMessage());
        }
    }

    /**
     * Saves the list in the fName file in text format suitable for later reading
     *
     * @param filePath
     */
    @Override
    public void save(String filePath) {
        throw new UnsupportedOperationException("Saving image .. is not currently supported");
    }

    /**
     * Image is printed to Ks.ouf ("")
     *
     * @param delimiter Image pair toString () string picker
     */
    @Override
    public void println(String delimiter) {
        if (super.isEmpty()) {
            Ks.oun("Image is empty");
            return;
        }

        Ks.oufln("****** Image ******");
        Ks.printMapModel(delimiter, getMapModel());
        Ks.oufln("****** The total number of pairs is " + super.size());
    }

    @Override
    public String[][] getMapModel() {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Entry<K, V> n : table) {
            List<String> list = new ArrayList<>();
            list.add("[ " + count + " ]");
            if (n != null && n.key != null) {
                list.add(ARROW);
                list.add(n.toString());
            }
            result[count++] = list.toArray(new String[0]);
        }
        return result;
    }

    private static <T, R> R create(Function<T, R> function, T data, String errorMessage) {
        return Optional.ofNullable(function)
                .map(f -> f.apply(data))
                .orElseThrow(() -> new IllegalStateException(errorMessage));
    }
}
