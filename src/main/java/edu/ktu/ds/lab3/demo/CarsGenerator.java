package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.gui.ValidationException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarsGenerator {

    private static final String ID_CODE = "TA";      //  ***** New
    private static int serNr = 100;                  //  ***** New

    private Queue<String> keys;
    private Queue<Car> cars;

    /**
     * Car and key queue is generated. This method is used in the graphical interface
     *
     * @param setSize Generated car and key row sizes
     * @throws ValidationException
     */
    public void generateShuffleIdsAndCars(int setSize) throws ValidationException {
        keys = generateShuffleIds(setSize);
        cars = generateShuffleCars(setSize);
    }

    /**
     * Returns one item (Car) from the generated Car Queue.
     * When items run out. New data is generated using same content and a message is thrown.
     * This method is used in the graphical interface
     *
     * @return
     */
    public Car getCar() {
        if (cars == null) {
            throw new ValidationException("carsNotGenerated");
        }
        if (cars.isEmpty()) {
            throw new ValidationException("allSetStoredToMap");
        }

        return cars.poll();
    }

    /**
     * Returns one key (ID) from the generated key queue.
     * When the keys run out, a self-generated situation is generated and a message is thrown.
     * This method is used in the graphical interface
     *
     * @return
     */
    public String getCarId() {
        if (keys == null) {
            throw new ValidationException("carsIdsNotGenerated");
        }
        if (keys.isEmpty()) {
            throw new ValidationException("allKeysStoredToMap");
        }

        return keys.poll();
    }

    public static Queue<Car> generateShuffleCars(int size) {
        LinkedList<Car> cars = IntStream.range(0, size)
                .mapToObj(i -> new Car.Builder().buildRandom())
                .collect(Collectors.toCollection(LinkedList::new));
        Collections.shuffle(cars);
        return cars;
    }

    public static Queue<String> generateShuffleIds(int size) {
        LinkedList<String> keys = IntStream.range(0, size)
                .mapToObj(i -> generateId())
                .collect(Collectors.toCollection(LinkedList::new));
        Collections.shuffle(keys);
        return keys;
    }

    public static String generateId() {
        return ID_CODE + (serNr++);
    }
}
