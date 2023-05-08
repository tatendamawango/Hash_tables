package edu.ktu.ds.lab3.gui;

/**
 * Own situation used to check the parameters entered in the dialogue structures.
 */
public class ValidationException extends RuntimeException {

    // Situacijos reikšmė
    private final String value;

    public ValidationException(String message) {
        this(message, "");
    }

    public ValidationException(String message, String value) {
        super(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
