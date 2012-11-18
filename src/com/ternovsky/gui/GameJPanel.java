package com.ternovsky.gui;

import com.ternovsky.model.Scene;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 18.11.12
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public class GameJPanel extends JPanel {

    private GameJFrame gameJFrame;
    private JPanel fieldJPanel;
    private Scene scene = Scene.getScene();

    public GameJPanel(GameJFrame gameJFrame) {
        this.gameJFrame = gameJFrame;
        setLayout(null);
        setBackground(Color.WHITE);
    }

    public void showScene() {
        showField();
        showMap();
        showTanks();
    }

    private void showTanks() {
    }

    private void showMap() {
        for (int i = 0; i < scene.getRowCount(); i++) {
            for (int j = 0; j < scene.getColumnCount(); j++) {
                fieldJPanel.add(new MapBlock(scene.getMap()[i][j], i, j));
            }
        }
        repaint();
    }

    private void showField() {
        fieldJPanel = new JPanel();
        fieldJPanel.setDoubleBuffered(true);
        fieldJPanel.setLayout(null);
        fieldJPanel.setBounds(0, 0, scene.getColumnCount() * MapBlock.SIZE, scene.getRowCount() * MapBlock.SIZE);
        add(fieldJPanel);
    }
}
