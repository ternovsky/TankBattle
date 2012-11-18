package com.ternovsky.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 18.11.12
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class GameJFrame extends JFrame {

    public static final String TITLE = "Tank Battle";
    public static int WIDTH = 600;
    public static int HEIGHT = 500;
    private final ControlJPanel controlJPanel;
    private final GameJPanel gameJPanel;

    public GameJFrame() {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());


        controlJPanel = new ControlJPanel(this);
        add(controlJPanel, BorderLayout.EAST);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());

        gameJPanel = new GameJPanel(this);
        layeredPane.add(gameJPanel, 0);

        AnimationCanvas animationCanvas = new AnimationCanvas();
        layeredPane.add(animationCanvas, 1);

        add(layeredPane);
    }

    public ControlJPanel getControlJPanel() {
        return controlJPanel;
    }

    public GameJPanel getGameJPanel() {
        return gameJPanel;
    }
}
