package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.gui.MainWindow;

import java.util.Locale;

/*
 * Work order - here is the Swing gui elementary class.
 */
public class DemoExecution {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // We unify number formats
        MainWindow.createAndShowGUI();
    }
}
