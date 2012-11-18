package com.ternovsky.gui;

import com.ternovsky.model.Coordinates;
import com.ternovsky.model.Direction;
import com.ternovsky.model.Scene;
import com.ternovsky.model.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 18.11.12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class GameCanvas extends Canvas implements Runnable {

    public static final int SIZE = 50;
    private boolean running;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private static int x = 0;
    private static int y = 0;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            render();
            update(delta);
        }
    }

    public void init() {
        addKeyListener(new KeyInputHandler());
        render();
        render();
    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        drawMap(g);
        drawUserTank(g);
        drawComputerTank(g);
        g.dispose();

        bufferStrategy.show();
    }

    private void drawUserTank(Graphics g) {
        Tank tank = Scene.getScene().getUserTank();
        g.setColor(Color.GREEN);
        drawTank(g, tank);
    }

    private void drawComputerTank(Graphics g) {
        Tank tank = Scene.getScene().getComputerTank();
        g.setColor(Color.RED);
        drawTank(g, tank);
    }

    private void drawTank(Graphics g, Tank tank) {
        Coordinates coordinates = tank.getCoordinates();
        Direction direction = tank.getDirection();
        int baseX = SIZE * coordinates.getColumn();
        int baseY = SIZE * coordinates.getRow();
        Polygon polygon = new Polygon();
        switch (direction) {
            case SOUTH: {
                polygon.addPoint(baseX, baseY);
                polygon.addPoint(baseX + 50, baseY);
                polygon.addPoint(baseX + 25, baseY + 50);
                break;
            }
            case EAST: {
                polygon.addPoint(baseX, baseY);
                polygon.addPoint(baseX, baseY + 50);
                polygon.addPoint(baseX + 50, baseY + 25);
                break;
            }
            case WEST: {
                polygon.addPoint(baseX + 50, baseY);
                polygon.addPoint(baseX + 50, baseY + 50);
                polygon.addPoint(baseX, baseY + 25);
                break;
            }
            case NORTH: {
                polygon.addPoint(baseX + 25, baseY + 0);
                polygon.addPoint(baseX + 0, baseY + 50);
                polygon.addPoint(baseX + 50, baseY + 50);
            }
        }
        g.fillPolygon(polygon);
    }

    private void drawMap(Graphics g) {
        Scene scene = Scene.getScene();
        for (int r = 0; r < scene.getRowCount(); r++) {
            for (int c = 0; c < scene.getColumnCount(); c++) {
                char character = scene.getMap()[r][c];
                if (character == Scene.SPACE) {
                    g.setColor(Color.BLACK);
                } else if (character == Scene.WALL) {
                    g.setColor(Color.LIGHT_GRAY);
                } else if (character == Scene.WATER) {
                    g.setColor(Color.CYAN);
                }
                g.fillRect(c * SIZE, r * SIZE, SIZE, SIZE);
            }
        }
    }

    public void update(long delta) {
        if (leftPressed == true) {
            x--;
        }
        if (rightPressed == true) {
            x++;
        }
    }

    /*public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }*/

    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
        }
    }
}
