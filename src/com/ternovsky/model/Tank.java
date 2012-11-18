package com.ternovsky.model;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 18.11.12
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
public class Tank {

    private Coordinates coordinates;
    private Direction direction = Direction.NORTH;

    public Tank(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
