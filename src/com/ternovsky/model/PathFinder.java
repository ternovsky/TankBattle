package com.ternovsky.model;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 20.11.12
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class PathFinder {

    private AlgorithmCoordinates[][] map;

    public PathFinder() {

    }

    private float distance(Coordinates coordinates1, Coordinates coordinates2) {
        int row1 = coordinates1.getRow();
        int column1 = coordinates1.getColumn();
        int row2 = coordinates2.getRow();
        int column2 = coordinates2.getColumn();
        return (float) Math.sqrt((column2 - column1) ^ 2 + (row2 - row1) ^ 2);
    }

    private Set<AlgorithmCoordinates> neighborCoordinates(AlgorithmCoordinates coordinates) {
        Set<AlgorithmCoordinates> coordinatesSet = new HashSet<AlgorithmCoordinates>();
        int row = coordinates.getRow();
        int column = coordinates.getColumn();
        addNotNull(coordinatesSet, row - 1, column - 1);
        addNotNull(coordinatesSet, row - 1, column);
        addNotNull(coordinatesSet, row - 1, column + 1);
        addNotNull(coordinatesSet, row, column - 1);
        addNotNull(coordinatesSet, row, column + 1);
        addNotNull(coordinatesSet, row + 1, column - 1);
        addNotNull(coordinatesSet, row + 1, column);
        addNotNull(coordinatesSet, row + 1, column + 1);
        return coordinatesSet;
    }

    private void addNotNull(Set<AlgorithmCoordinates> coordinatesSet, int row, int column) {
        AlgorithmCoordinates coordinates;
        try {
            coordinates = map[row][column];
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (coordinates != null) {
            coordinatesSet.add(coordinates);
        }
    }

    public List<Coordinates> buildPath(Coordinates startCoordinates, Coordinates finishCoordinates) {
        init(finishCoordinates);
        AlgorithmCoordinates start = map[startCoordinates.getRow()][startCoordinates.getColumn()];
        AlgorithmCoordinates finish = map[finishCoordinates.getRow()][finishCoordinates.getColumn()];

        Set<AlgorithmCoordinates> openedCoordinates = new TreeSet<AlgorithmCoordinates>();
        Set<AlgorithmCoordinates> closedCoordinates = new HashSet<AlgorithmCoordinates>();
        openedCoordinates.add(start);

        start.path = 0;
        start.distance = distance(start, finish);
        start.heuristic = start.path + start.distance;

        while (!openedCoordinates.isEmpty()) {
            AlgorithmCoordinates coordinates = openedCoordinates.iterator().next();
            if (coordinates.equals(finish)) {
                return recreatePath(finish);
            }
            openedCoordinates.remove(coordinates);
            closedCoordinates.add(coordinates);
            for (AlgorithmCoordinates c : neighborCoordinates(coordinates)) {
                if (closedCoordinates.contains(c)) {
                    continue;
                }
                float distanceToNeighbor = coordinates.path + distance(coordinates, c);
                boolean isAdded = false;
                if (!openedCoordinates.contains(c)) {
                    openedCoordinates.add(c);
                    isAdded = true;
                }
                if (distanceToNeighbor < c.path || isAdded) {
                    c.previousCoordinates = coordinates;
                    c.distance = distanceToNeighbor;
                    c.path = distance(c, finish);
                    c.heuristic = c.distance + c.path;
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinates> recreatePath(AlgorithmCoordinates coordinates) {
        List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
        AlgorithmCoordinates currentCoordinates = coordinates;
        while (currentCoordinates.previousCoordinates != null) {
            coordinatesList.add(new Coordinates(currentCoordinates.getRow(), currentCoordinates.getColumn()));
            currentCoordinates = currentCoordinates.previousCoordinates;
        }
        return coordinatesList;
    }

    private void init(Coordinates finish) {
        Scene scene = Scene.getScene();
        int rowCount = scene.getRowCount();
        int columnCount = scene.getColumnCount();
        map = new AlgorithmCoordinates[rowCount][columnCount];
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                char character = scene.getMap()[r][c];
                if (character == Scene.SPACE || character == Scene.COMPUTER_TANK || character == Scene.USER_TANK) {
                    AlgorithmCoordinates coordinates = new AlgorithmCoordinates(r, c);
                    coordinates.distance = distance(coordinates, finish);
                    map[r][c] = coordinates;
                }
            }
        }
    }

    private class AlgorithmCoordinates extends Coordinates implements Comparable<AlgorithmCoordinates> {

        float path;
        float distance;
        float heuristic;
        AlgorithmCoordinates previousCoordinates;

        public AlgorithmCoordinates(int row, int column) {
            super(row, column);
        }

        @Override
        public int compareTo(AlgorithmCoordinates o) {
            if (this.equals(o)) {
                return 0;
            } else {
                return this.distance > o.distance ? 1 : -1;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (path != +0.0f ? Float.floatToIntBits(path) : 0);
            result = 31 * result + (distance != +0.0f ? Float.floatToIntBits(distance) : 0);
            result = 31 * result + (heuristic != +0.0f ? Float.floatToIntBits(heuristic) : 0);
            result = 31 * result + (previousCoordinates != null ? previousCoordinates.hashCode() : 0);
            return result;
        }
    }
}