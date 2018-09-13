package com.pl.mychess.domain.model.chessboard;

import java.util.Objects;

public class Figure {
    private TypeOfFigure typeOfFigure;
    private final ColorOfFigure colorOfFigure;
    private boolean isMoved;
    private boolean isBeaten;

    public Figure(TypeOfFigure typeOfFigure, ColorOfFigure colorOfFigure) {
        this.typeOfFigure = typeOfFigure;
        this.colorOfFigure = colorOfFigure;
        this.isMoved = false;
        this.isBeaten = false;
    }

    public Figure(Figure figure) {
        this.typeOfFigure = figure.getTypeOfFigure();
        this.colorOfFigure = figure.getColorOfFigure();
        this.isMoved = figure.isMoved();
        this.isBeaten = figure.isBeaten();
    }

    public TypeOfFigure getTypeOfFigure() {
        return typeOfFigure;
    }

    public ColorOfFigure getColorOfFigure() {
        return colorOfFigure;
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
                colorOfFigure == figure.colorOfFigure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfFigure, colorOfFigure);
    }

    @Override
    public String toString() {
        return colorOfFigure + " " + typeOfFigure.getSignature();
    }
}
