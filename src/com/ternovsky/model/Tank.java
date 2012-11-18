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

    public Tank() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Tank(Coordinates coordinates) {

        this.coordinates = coordinates;
    }
}
