package edu.ktu.ds.lab3.utils;

/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra
 * <p>
 * This is the interface that KTU student-created data classes must meet. Methods ensure convenient data
 * generation from String strings
 ******************************************************************************/
public interface Parsable<T> {

    /**
     * Forms an object from a line of text
     *
     * @param dataString
     */
    void parse(String dataString);
}
