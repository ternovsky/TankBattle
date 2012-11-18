package com.ternovsky.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 15.11.12
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class Scene {

    public static char WATER = 'w';
    public static char WALL = 's';
    public static char SPACE = ' ';
    public static char USER_TANK = 'x';
    public static char COMPUTER_TANK = 'y';

    private static Scene scene;

    private int rowCount;
    private int columnCount;
    private char[][] map;
    private Tank userTank;
    private Tank computerTank;

    private Scene() {
    }

    public static Scene getScene() {
        if (scene == null) {
            scene = new Scene();
        }
        return scene;
    }

    public Tank getUserTank() {
        return userTank;
    }

    public Tank getComputerTank() {
        return computerTank;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public char[][] getMap() {
        return map;
    }

    public void readScene(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        rowCount = scanner.nextInt();
        columnCount = scanner.nextInt();
        scanner.nextLine();
        map = new char[rowCount][columnCount];
        for (int r = 0; r < rowCount; r++) {
            char[] rowChars = scanner.nextLine().toCharArray();
            for (int c = 0; c < columnCount; c++) {
                try {
                    char character = Character.toLowerCase(rowChars[c]);
                    map[r][c] = character;
                    if (character == USER_TANK) {
                        userTank = new Tank(new Coordinates(r, c));
                    } else if (character == COMPUTER_TANK) {
                        computerTank = new Tank(new Coordinates(r, c));
                    }
                } catch (IndexOutOfBoundsException e) {
                    map[r][c] = SPACE;
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                stringBuilder.append(map[r][c]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
