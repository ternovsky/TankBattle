package com.ternovsky.gui;

import com.ternovsky.model.Scene;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 18.11.12
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class MapBlock extends JPanel {

    public static final int SIZE = 50;

    public MapBlock(char type, int row, int column) {
        setSize(SIZE, SIZE);
        setBackground(Color.BLACK);
        if (type == Scene.WALL) {
            setBackground(Color.LIGHT_GRAY);
        } else if (type == Scene.WATER) {
            setBackground(Color.CYAN);
        }
        setBounds(column * SIZE, row * SIZE, SIZE, SIZE);
    }
}
