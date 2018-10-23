package com.pl.mychess.domain.model.chessboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Chessboard {
    private Place[][] placesOfChessboard;
    private List<Figure> figures;

    public Chessboard() {
        this.placesOfChessboard = new Place[8][8];
        this.figures = new ArrayList<>();
    }

    public Chessboard(Place[][] placesOfChessboard, List<Figure> figures) {
        this.placesOfChessboard = placesOfChessboard;
        this.figures = figures;
    }

    public void setFigureInPlace(char coordinateX, int coordinateY, Figure figureToSet) {
        getPlaceByCoordinates(coordinateX, coordinateY).setCurrentFigure(figureToSet);
        figures.add(figureToSet);
    }

    public Place[][] getPlacesOfChessboard() {
        return placesOfChessboard;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public Place getPlaceByCoordinates(char x, int y) {
        int indexForCoordinateX = (int) x - (int) 'a';
        int indexForCoordinateY = y - 1;
        return placesOfChessboard[indexForCoordinateY][indexForCoordinateX];
    }

    public Figure getFigureByCoordinates(char x, int y) {
        int indexForCoordinateX = (int) x - (int) 'a';
        int indexForCoordinateY = y - 1;
        return placesOfChessboard[indexForCoordinateY][indexForCoordinateX].getCurrentFigure();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chessboard that = (Chessboard) o;
        return Arrays.equals(placesOfChessboard, that.placesOfChessboard) &&
                Objects.equals(figures, that.figures);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(figures);
        result = 31 * result + Arrays.hashCode(placesOfChessboard);
        return result;
    }
}
