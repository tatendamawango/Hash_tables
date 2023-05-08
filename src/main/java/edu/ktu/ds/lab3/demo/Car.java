package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.Parsable;

import java.time.LocalDate;
import java.util.*;

/**
 * @author EK
 */
public final class Car implements Parsable<Car> {

    // General data for all cars (for the whole class)
    private static final int minYear = 2000;
    private static final int currentYear = LocalDate.now().getYear();
    private static final double minPrice = 100.0;
    private static final double maxPrice = 333000.0;

    private String make = "";
    private String model = "";
    private int year = -1;
    private int mileage = -1;
    private double price = -1.0;

    public Car() {
    }

    public Car(String make, String model, int year, int mileage, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        validate();
    }

    public Car(String dataString) {
        this.parse(dataString);
        validate();
    }

    public Car(Builder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.mileage = builder.mileage;
        this.price = builder.price;
        validate();
    }

    private void validate() {
        String errorType = "";
        if (year < minYear || year > currentYear) {
            errorType = "Invalid year of manufacture = ["
                    + minYear + ":" + currentYear + "]";
        }
        if (price < minPrice || price > maxPrice) {
            errorType += " Price outside the allowable range [" + minPrice
                    + ":" + maxPrice + "]";
        }

        if (!errorType.isEmpty()) {
            Ks.ern("The car is badly generated: " + errorType + ". " + this);
        }
    }

    @Override
    public void parse(String dataString) {
        try {   // data separated by spaces
            Scanner scanner = new Scanner(dataString);
            make = scanner.next();
            model = scanner.next();
            year = scanner.nextInt();
            mileage = scanner.nextInt();
            price = scanner.nextDouble();
        } catch (InputMismatchException e) {
            Ks.ern("Bad data format -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Missing data -> " + dataString);
        }
    }

    @Override
    public String toString() {
        return make + "_" + model + ":" + year + " " + getMileage() + " "
                + String.format("%4.1f", price);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
                mileage == car.mileage &&
                Double.compare(car.price, price) == 0 &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, mileage, price);
    }

    // Manufacturer of car classes items
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Random generator
        private final static String[][] MODELS = { // an array of possible car brands and models
                {"Mazda", "3", "6", "CX-3", "MX-5"},
                {"Ford", "Fiesta", "Kuga", "Focus", "Galaxy", "Mondeo"},
                {"VW", "Golf", "Jetta", "Passat", "Tiguan"},
                {"Honda", "HR-V", "CR-V", "Civic", "Jazz"},
                {"Renault", "Clio", "Megane", "Twingo", "Scenic"},
                {"Peugeot", "208", "308", "508", "Partner"},
                {"Audi", "A3", "A4", "A6", "A8", "Q3", "Q5"}
        };

        private String make = "";
        private String model = "";
        private int year = -1;
        private int mileage = -1;
        private double price = -1.0;

        public Car build() {
            return new Car(this);
        }

        public Car buildRandom() {
            int ma = RANDOM.nextInt(MODELS.length);        // brand index 0 ..
            int mo = RANDOM.nextInt(MODELS[ma].length - 1) + 1;// model index 1 ..
            return new Car(MODELS[ma][0],
                    MODELS[ma][mo],
                    2000 + RANDOM.nextInt(22),// year between 2000 and 2021
                    6000 + RANDOM.nextInt(222000),// mileage between 6000 and 228000
                    800 + RANDOM.nextDouble() * 88000);// price between 800 and 88800
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }
    }
}
