package edu.ktu.ds.lab3.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * A class-specific table for displaying a hash table.
 *
 * @author darius
 */
public class Table extends JTable {

    public static final String ARROW = "\u2794";

    public void setModel(String[][] tableContent, String delimiter, int maxChainSize, int colWidth) {
        if (tableContent == null || delimiter == null) {
            throw new IllegalArgumentException("Table content or delimiter is null");
        }

        if (maxChainSize < 0 || colWidth < 0) {
            throw new IllegalArgumentException("Table column width or max chain size is <0: " + colWidth + ", " + maxChainSize);
        }

        setModel(new TableModel(tableContent, delimiter, maxChainSize));
        appearance(colWidth);
    }

    private void appearance(int colWidth) {
        setShowGrid(false);
        //Cell style - we focus
        DefaultTableCellRenderer toCenter = new DefaultTableCellRenderer();
        toCenter.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < getColumnCount(); i++) {
            if (i == 0) {
                getColumnModel().getColumn(i).setPreferredWidth(1);
                // Set the zero column cell style
                getColumnModel().getColumn(i).setCellRenderer(toCenter);
            } else if (i % 2 != 0) {
                getColumnModel().getColumn(i).setPreferredWidth(30);
                // Set the cell style for columns with arrows
                getColumnModel().getColumn(i).setCellRenderer(toCenter);
            } else {
                getColumnModel().getColumn(i).setMaxWidth(colWidth);
                getColumnModel().getColumn(i).setMinWidth(colWidth);
            }
        }

        //Table headings
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        //Centering headers
        ((DefaultTableCellRenderer) getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        //Sets the display of tooltips
        String value = (String) getValueAt(row, column);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent) c;
            jc.setToolTipText(value);
        }
         if (value != null && !value.equals("") && !value.equals(ARROW)) {
            c.setBackground(Color.ORANGE);
        }
        else {
            c.setBackground(Color.WHITE);
        }

        int rendererWidth = c.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return c;
    }
}
