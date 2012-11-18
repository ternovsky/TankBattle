package com.ternovsky.gui;

import com.ternovsky.model.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 18.11.12
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class ControlJPanel extends JPanel {

    public static final String LOAD_MAP = "Загрузить карту";
    public static final String START = "Старт";

    private final JButton loadMapJButton;
    private final JButton startJButton;

    public ControlJPanel(final GameJFrame gameJFrame) {
        setLayout(new FlowLayout());

        loadMapJButton = new JButton(LOAD_MAP);
        loadMapJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser mapJFileChooser = new JFileChooser();
                mapJFileChooser.setCurrentDirectory(new File("./src"));
                mapJFileChooser.showDialog(null, LOAD_MAP);
                try {
                    Scene scene = Scene.getScene();
                    scene.readScene(mapJFileChooser.getSelectedFile());
                    gameJFrame.getGameCanvas().init();
                } catch (FileNotFoundException e1) {
                }
            }
        });
        add(loadMapJButton);

        startJButton = new JButton(START);
        startJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameJFrame.getGameCanvas().start();
            }
        });
        add(startJButton);
    }
}
