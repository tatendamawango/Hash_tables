/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

/**
 * @author darius
 */
public abstract class MainWindowMenu extends JMenuBar implements ActionListener {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab3.gui.messages");

    public MainWindowMenu() {
        initComponents();
    }

    private void initComponents() {
        // A menu bar is created
        JMenu jMenu1 = new JMenu(MESSAGES.getString("menu1"));
        super.add(jMenu1);
        JMenuItem jMenuItem11 = new JMenuItem(MESSAGES.getString("menuItem11"));
        jMenuItem11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jMenuItem12 = new JMenuItem(MESSAGES.getString("menuItem12"));
        jMenuItem12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jMenuItem13 = new JMenuItem(MESSAGES.getString("menuItem13"));
        jMenuItem13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        jMenu1.add(jMenuItem11);
        jMenu1.add(jMenuItem12);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem13);

        JMenu jMenu2 = new JMenu(MESSAGES.getString("menu2"));
        super.add(jMenu2);
        JMenuItem jMenuItem21 = new JMenuItem(MESSAGES.getString("menuItem21"));
        jMenuItem21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_DOWN_MASK));
        jMenu2.add(jMenuItem21);

        jMenuItem11.addActionListener(this);
        jMenuItem12.addActionListener(this);
        jMenuItem13.addActionListener(this);
        jMenuItem21.addActionListener(this);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
