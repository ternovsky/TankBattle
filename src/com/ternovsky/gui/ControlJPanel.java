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
    private final JButton loadMapJButton;

    public ControlJPanel(final GameJFrame gameJFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
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
                    gameJFrame.getGameJPanel().showScene();
                } catch (FileNotFoundException e1) {
                }
            }
        });
        add(loadMapJButton);
    }
}
