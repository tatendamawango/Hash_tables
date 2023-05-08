package edu.ktu.ds.lab3.gui;

import javax.swing.table.AbstractTableModel;

/**
 * Swing table model class
 */
public class TableModel extends AbstractTableModel {

    private final String delimiter;
    private final String[][] table;
    private final int maxChainSize;

    public TableModel(String[][] table, String delimiter, int maxChainSize) {
        this.table = table;
        this.delimiter = delimiter;
        this.maxChainSize = maxChainSize;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (row < table.length && col < table[row].length && table[row][col] != null) {
            return split(table[row][col], delimiter);
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        if (col == 0) {
            return "#";
        }
        if (col % 2 == 0) {
            return "(" + (col / 2 - 1) + ")";
        }
        return "";
    }

    @Override
    public int getColumnCount() {
        return maxChainSize * 2 + 1;
    }

    @Override
    public int getRowCount() {
        return table.length;
    }

    private String split(String s, String delimiter) {
        int k = s.indexOf(delimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }
}
