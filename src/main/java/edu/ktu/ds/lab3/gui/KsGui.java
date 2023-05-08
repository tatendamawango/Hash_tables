package edu.ktu.ds.lab3.gui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * A class for outputting data to the Swing GUI
 */
public class KsGui {

    private static int lineNr;
    private static boolean formatStartOfLine = true;

    private static String getStartOfLine() {
        return (formatStartOfLine) ? ++lineNr + "| " : "";
    }

    public static void setFormatStartOfLine(boolean formatStartOfLine) {
        KsGui.formatStartOfLine = formatStartOfLine;
    }

    public static void ou(JTextArea ta, Object o) {
        StringBuilder sb = new StringBuilder();
        if (o instanceof Iterable) {
            ((Iterable) o).forEach(p -> sb.append(p).append(System.lineSeparator()));
        } else {
            sb.append(o.toString());
        }
        ta.append(sb.toString());
    }

    public static void oun(JTextArea ta, Object o) {
        ou(ta, o);
        ta.append(System.lineSeparator());
    }

    public static void ou(JTextArea ta, Object o, String msg) {
        String startOfLine = getStartOfLine();
        ta.append(startOfLine + msg + ": ");
        oun(ta, o);
    }

    public static void oun(JTextArea ta, Object o, String msg) {
        String startOfLine = getStartOfLine();
        ta.append(startOfLine + msg + ": " + System.lineSeparator());
        oun(ta, o);
    }

    public static void ounArgs(JTextArea ta, String format, Object... args) {
        String startOfLine = getStartOfLine();
        ta.append(startOfLine + String.format(format, args) + System.lineSeparator());
    }

    public static void ounerr(JTextArea ta, Exception e) {
        ta.setBackground(Color.pink);
        String startOfLine = getStartOfLine();
        ta.append(startOfLine + e.getLocalizedMessage() + System.lineSeparator());
    }

    public static void ounerr(JTextArea ta, String msg) {
        ta.setBackground(Color.pink);
        String startOfLine = getStartOfLine();
        ta.append(startOfLine + msg + System.lineSeparator());
    }

    public static void ounerr(JTextArea ta, String msg, String parameter) {
        ta.setBackground(Color.pink);
        String startOfLine = getStartOfLine();
        ta.append(startOfLine + msg + ((parameter == null || parameter.isEmpty())
                ? "" : ": " + parameter) + System.lineSeparator());
    }
}
