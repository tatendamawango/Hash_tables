package edu.ktu.ds.lab3.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * You can create two panels for a class object: a parameter table and a grid of buttons.
 * The number of objects to be stored in the panel is determined by parameters.
 *
 * @author darius.matulis@ktu.lt
 */
public class Panels extends JPanel {

    private final static int SPACING = 4;
    private final List<JTextField> tfs = new ArrayList<>();
    private final List<JButton> btns = new ArrayList<>();
    private List<String> tfTexts = new ArrayList<>();

    /**
     * Creating a parameter table (GridBag layout law)
     * <pre>
     * |-------------------------------|
     * |                |------------| |
     * |   lblTexts[0]  | tfTexts[0] | |
     * |                |------------| |
     * |                               |
     * |                |------------| |
     * |   lblTexts[1]  | tfTexts[1] | |
     * |                |------------| |
     * |      ...             ...      |
     * |-------------------------------|
     * </pre>
     *
     * @param lblTexts
     * @param tfTexts
     * @param columnWidth
     */
    public Panels(String[] lblTexts, String[] tfTexts, int columnWidth) {
        super();
        if (lblTexts == null || tfTexts == null) {
            throw new IllegalArgumentException("Arguments for table parameters are incorrect");
        }
        this.tfTexts = Arrays.stream(tfTexts).collect(Collectors.toList());
        List<String> lblTextsList = Arrays.stream(lblTexts).collect(Collectors.toList());
        if (lblTextsList.size() > this.tfTexts.size()) {
            this.tfTexts = Stream.concat(Arrays.stream(tfTexts),
                    Arrays.stream(new String[lblTextsList.size() - this.tfTexts.size()])).collect(Collectors.toList());
        }
        initTableOfParameters(columnWidth, lblTextsList);
    }

    /**
     *
     * Creating a grid of buttons (GridLayout layout law)
     * <pre>
     * |-------------------------------------|
     * | |-------------| |-------------|     |
     * | | btnNames[0] | | btnNames[1] | ... |
     * | |-------------| |-------------|     |
     * |                                     |
     * | |-------------| |-------------|     |
     * | | btnNames[2] | | btnNames[3] | ... |
     * | |-------------| |-------------|     |
     * |       ...              ...          |
     * |-------------------------------------|
     * </pre>
     *
     * @param btnNames
     * @param gridX
     * @param gridY
     */
    public Panels(String[] btnNames, int gridX, int gridY) {
        super();
        if (btnNames == null || gridX < 1 || gridY < 1) {
            throw new IllegalArgumentException("Arguments for buttons grid is incorrect");
        }
        initGridOfButtons(gridX, gridY, Arrays.stream(btnNames).collect(Collectors.toList()));
    }

    private void initTableOfParameters(int columnWidth, List<String> lblTexts) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Spacing between components
        c.insets = new Insets(SPACING, SPACING, SPACING, SPACING);
        // Lygiavimas į kairę
        c.anchor = GridBagConstraints.WEST;
        //
        //The first column is selected.
        c.gridx = 0;
        // and labels are placed on it
        lblTexts.forEach((lblText) -> add(new JLabel(lblText), c));
        // Optional second column.
        c.gridx = 1;
        // ..and textfields are added to it
        for (String tfText : tfTexts) {
            JTextField tf = new JTextField(tfText, columnWidth);
            tf.setHorizontalAlignment(JTextField.CENTER);
            tfs.add(tf);
            add(tf, c);
        }
    }

    private void initGridOfButtons(int gridX, int gridY, List<String> btnNames) {
        setLayout(new GridLayout(gridY, gridX, SPACING, SPACING));
        int nameIndex = 0;
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridY; j++) {
                if (nameIndex >= btnNames.size()) {
                    break;
                }
                JButton button = new JButton(btnNames.get(nameIndex));
                btns.add(button);
                add(button);
                nameIndex++;
            }
        }
    }

    /**
     * The list of parameters in the parameter table is returned
     *
     * @return The list of parameters in the parameter table is returned
     */
    public List<String> getParametersOfTable() {
        tfTexts.clear();
        tfs.forEach(tf -> tfTexts.add(tf.getText()));
        return tfTexts;
    }

    /**
     * Returns the list of JTextField objects in the parameter table
     *
     * @return Returns the list of JTextField objects in the parameter table
     */
    public List<JTextField> getTfOfTable() {
        return tfs;
    }

    /**
     * Returns the list of button grid JButton objects
     *
     * @return Returns the list of button grid JButton objects
     */
    public List<JButton> getButtons() {
        return btns;
    }
}