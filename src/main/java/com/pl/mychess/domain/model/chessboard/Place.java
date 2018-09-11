package com.pl.mychess.domain.model.chessboard;

import java.util.Objects;

public class Place { //TODO dodac Clonable
    private final char coordinateX;
    private final int coordinateY;
    private Figure currentFigure;

    public Place(char coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.currentFigure = null;
    }

    public Place(char coordinateX, int coordinateY, Figure currentFigure) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.currentFigure = currentFigure;
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
                coordinateY == place.coordinateY &&
                Objects.equals(currentFigure, place.currentFigure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY, currentFigure);
    }

    @Override
    public String toString() {
        return coordinateX + String.valueOf(coordinateY);
    }
}
