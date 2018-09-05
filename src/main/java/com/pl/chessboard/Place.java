package com.pl.chessboard;

import java.util.Objects;

public class Place {
    private final char coordinateX;
    private final int coordinateY;
    private Figure currentFigure;

    public Place(char coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.currentFigure = null;
    }

    public char getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return coordinateX == place.coordinateX &&
                coordinateY == place.coordinateY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return coordinateX + String.valueOf(coordinateY);
    }
}
