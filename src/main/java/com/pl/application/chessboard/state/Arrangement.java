package com.pl.application.chessboard.state;

import com.pl.application.chessboard.Figure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arrangement {
    private Map<String, Figure> figuresArrangement;

    public Arrangement() {
        this.figuresArrangement = new HashMap<>();
    }

    public Map<String, Figure> getFiguresArrangement() {
        return figuresArrangement;
    }

    public void createArrangement() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char coordinateX = (char) ((int) 'a' + x);
                int coordinateY = y + 1;
                String descriptionOfPlace = String.valueOf(coordinateX) + String.valueOf(coordinateY);
                Figure figure = FigureFactory.createFigure(coordinateX, coordinateY);
                figuresArrangement.put(descriptionOfPlace, figure);
            }
        }
    }

    public void createArrangement(Arrangement startedArrangement, List<Move> historyOfMoves) {
        Arrangement previousArrangement = startedArrangement;
        Arrangement arrangement = new Arrangement();

        for(Move move : historyOfMoves) {
            arrangement.createArrangement(previousArrangement, move);
            previousArrangement = arrangement;
        }

        figuresArrangement = arrangement.getFiguresArrangement();
    }

    public void createArrangement(Arrangement lastArrangement, Move move) {

    }
}