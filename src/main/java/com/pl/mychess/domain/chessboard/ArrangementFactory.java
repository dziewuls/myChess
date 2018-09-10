package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Move;
import com.pl.mychess.domain.model.chessboard.Arrangement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrangementFactory {
    public static Arrangement createArrangement() {
        Map<String, Figure> figuresArrangement = new HashMap<>();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char coordinateX = (char) ((int) 'a' + x);
                int coordinateY = y + 1;
                String descriptionOfPlace = String.valueOf(coordinateX) + String.valueOf(coordinateY);
                Figure figure = FigureFactory.createFigureByStartedPosition(coordinateX, coordinateY);
                figuresArrangement.put(descriptionOfPlace, figure);
            }
        }
        return new Arrangement(figuresArrangement);
    }

    public static Arrangement createArrangement(Arrangement startedArrangement, List<Move> historyOfMoves) {
        Arrangement previousArrangement = startedArrangement;
        Arrangement arrangement = new Arrangement();
        Map<String, Figure> figuresArrangement = new HashMap<>();

        for(Move move : historyOfMoves) {
            createArrangement(previousArrangement, move);
            previousArrangement = arrangement;
        }

        figuresArrangement = arrangement.getFiguresArrangement();

        return new Arrangement(figuresArrangement);
    }

    public static Arrangement createArrangement(Arrangement lastArrangement, Move move) {
        //TODO wypelnic

        return new Arrangement();
    }
}
