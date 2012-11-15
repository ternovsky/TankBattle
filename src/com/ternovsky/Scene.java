package com.ternovsky;

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

    public void readScene(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        rowCount = scanner.nextInt();
        columnCount = scanner.nextInt();
        scanner.nextLine();
        scene = new char[rowCount][columnCount];
        for (int r = 0; r < rowCount; r++) {
            char[] rowChars = scanner.nextLine().toCharArray();
            for (int c = 0; c < columnCount; c++) {
                try {
                    scene[r][c] = Character.toLowerCase(rowChars[c]);
                } catch (IndexOutOfBoundsException e) {
                    scene[r][c] = SPACE;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                stringBuilder.append(scene[r][c]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private int rowCount;
    private int columnCount;
    private char[][] scene;
}
