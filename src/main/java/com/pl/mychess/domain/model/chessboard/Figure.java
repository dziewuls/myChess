package com.pl.mychess.domain.model.chessboard;

import java.util.Objects;

public class Figure {
    private TypeOfFigure typeOfFigure;
    private final Color color;
    private boolean isMoved;
    private boolean isBeaten;

    public Figure(TypeOfFigure typeOfFigure, Color color) {
        this.typeOfFigure = typeOfFigure;
        this.color = color;
        this.isMoved = false;
        this.isBeaten = false;
    }

    public Figure(Figure figure) {
        this.typeOfFigure = figure.getTypeOfFigure();
        this.color = figure.getColor();
        this.isMoved = figure.isMoved();
        this.isBeaten = figure.isBeaten();
    }

    public TypeOfFigure getTypeOfFigure() {
        return typeOfFigure;
    }

    public Color getColor() {
        return color;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public boolean isBeaten() {
        return isBeaten;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public void setBeaten(boolean beaten) {
        isBeaten = beaten;
    }

    public void setTypeOfFigure(TypeOfFigure typeOfFigure) {
        this.typeOfFigure = typeOfFigure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return typeOfFigure == figure.typeOfFigure &&
                color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfFigure, color);
    }

    @Override
    public String toString() {
        return typeOfFigure.getSignature();
    }
}
