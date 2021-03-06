package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class StateOfGameToolsValidator {
    private StateOfGameToolsValidator() {
    }

    static Place findTheKingPlace(Chessboard chessboard, Color currentColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure tmpFigure = chessboard.getFigureByCoordinates((char) ('a' + j), 1 + i);
                if(tmpFigure == null) continue;
                if (tmpFigure.getTypeOfFigure() == TypeOfFigure.KING && tmpFigure.getColor() == currentColor) {
                    return chessboard.getPlaceByCoordinates((char) ('a' + j), 1 + i);
                }
            }
        }

        return null;
    }

    static boolean isThePlaceAttacked(Chessboard chessboard, Place testedPlace, Color currentColor) {
        Figure tmpFigure = new Figure(TypeOfFigure.PAWN, currentColor);
        testedPlace.setCurrentFigure(tmpFigure);
        boolean isAttacked = isTheFigureAttacked(chessboard, testedPlace);
        testedPlace.setCurrentFigure(null);
        return isAttacked;
    }

    static boolean isTheFigureAttacked(Chessboard chessboard, Place placeOfCheckedFigure) {
        if(placeOfCheckedFigure == null) return false;

        Figure checkedFigure = chessboard.getFigureByCoordinates(
                placeOfCheckedFigure.getCoordinateX(), placeOfCheckedFigure.getCoordinateY());
        Color currentColor = checkedFigure.getColor();

        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (isOpponentFigureAttackTheGivenFigure(chessboard, placeOfCheckedFigure, currentColor, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean isOpponentFigureAttackTheGivenFigure(Chessboard chessboard, Place placeOfCheckedFigure, Color color, int i, char j) {
        Figure opponentFigure = chessboard.getFigureByCoordinates(j, i);
        Place placeOfOpponentFigure = chessboard.getPlaceByCoordinates(j, i);
        if (opponentFigure == null)
            return false;
        if (opponentFigure.getColor() != color) {
            List<Place> placesAttackedByOpponentFigure = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, placeOfOpponentFigure);
            return placesAttackedByOpponentFigure.contains(placeOfCheckedFigure);
        }
        return false;
    }

    static boolean hasTheCurrentPlayerAnyCorrectMove(Chessboard chessboard, Color currentColor) {
        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (hasTheFigureAnyCorrectMove(chessboard, currentColor, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasTheFigureAnyCorrectMove(Chessboard chessboard, Color currentColor, int i, char j) {
        Place placeOfCheckedFigure = chessboard.getPlaceByCoordinates(j, i);
        Figure checkedFigure = chessboard.getFigureByCoordinates(j, i);
        if (checkedFigure != null && checkedFigure.getColor() == currentColor) {
            Map<Place, TypeOfCustomMove> placesMap = (new ClassicChessGameValidator()).getCorrectPlacesForFigure(chessboard, placeOfCheckedFigure, null);
            List<Place> correctPlaces = new ArrayList<>(placesMap.keySet());
            return !correctPlaces.isEmpty();
        }
        return false;
    }

    static boolean isInsufficientMaterialForMate(Chessboard chessboard) {
        int whiteKnights = 0;
        int whiteBishops = 0;
        int blackKnights = 0;
        int blackBishops = 0;

        List<Figure> notBeatenFigures = chessboard.getFigures()
                .stream()
                .filter(f -> !f.isBeaten() && f.getTypeOfFigure() != TypeOfFigure.KING)
                .collect(Collectors.toList());

        for (Figure f : notBeatenFigures) {
            if (f.equals(new Figure(TypeOfFigure.BISHOP, Color.WHITE))) {
                whiteBishops++;
            } else if (f.equals(new Figure(TypeOfFigure.BISHOP, Color.BLACK))) {
                blackBishops++;
            } else if (f.equals(new Figure(TypeOfFigure.KNIGHT, Color.WHITE))) {
                whiteKnights++;
            } else if (f.equals(new Figure(TypeOfFigure.KNIGHT, Color.BLACK))) {
                blackKnights++;
            } else {
                return false;
            }
        }

        boolean whiteHasNotEnoughFigures = whiteBishops == 0 || (whiteBishops == 1 && whiteKnights == 0);
        boolean blackHasNotEnoughFigures = blackBishops == 0 || (blackBishops == 1 && blackKnights == 0);

        return whiteHasNotEnoughFigures && blackHasNotEnoughFigures;
    }

    static boolean isThePlaceExist(char corX, int corY) {
        return corX >= 'a' && corX <= 'h' && corY >= 1 && corY <= 8;
    }
}
