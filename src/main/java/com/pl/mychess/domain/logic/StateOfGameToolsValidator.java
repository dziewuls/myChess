package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.*;

import java.util.List;

class StateOfGameToolsValidator {
    private StateOfGameToolsValidator(){}

    static Figure findTheKing(Chessboard chessboard, ColorOfFigure currentColor) {
        for (Figure f : chessboard.getFigures()) {
            if (f.getTypeOfFigure() == TypeOfFigure.KING && f.getColorOfFigure() == currentColor) {
                return f;
            }
        }
        return null;
    }

    static boolean isThePlaceAttacked(Chessboard chessboard, Place testedPlace, ColorOfFigure currentColor){
        //TODO przetestować
        Figure tmpFigure = new Figure(TypeOfFigure.PAWN, currentColor);
        testedPlace.setCurrentFigure(tmpFigure);
        boolean isAttacked = isTheFigureAttacked(chessboard, tmpFigure);
        testedPlace.setCurrentFigure(null);
        return isAttacked;
    }

    static boolean isTheFigureAttacked(Chessboard chessboard, Figure checkedFigure) {
        Place placeOfCheckedFigure = chessboard.getPlaceForGivenFigure(checkedFigure);
        ColorOfFigure currentColor = checkedFigure.getColorOfFigure();

        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (isOpponentFigureAttackTheGivenFigure(chessboard, placeOfCheckedFigure, currentColor, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean isOpponentFigureAttackTheGivenFigure(Chessboard chessboard, Place placeOfCheckedFigure, ColorOfFigure colorOfFigure, int i, char j) {
        Figure opponentFigure = chessboard.getFigureByCoordinates(j, i);
        if (opponentFigure == null)
            return false;
        if (opponentFigure.getColorOfFigure() != colorOfFigure) {
            List<Place> placesAttackedByOpponentFigure = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, opponentFigure);
            return placesAttackedByOpponentFigure.contains(placeOfCheckedFigure);
        }
        return false;
    }

    static boolean hasTheCurrentPlayerAnyCorrectMove(Chessboard chessboard, ColorOfFigure currentColor) {
        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (hasTheFigureAnyCorrectMove(chessboard, currentColor, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasTheFigureAnyCorrectMove(Chessboard chessboard, ColorOfFigure currentColor, int i, char j) {
        Figure checkedFigure = chessboard.getFigureByCoordinates(j, i);
        if (checkedFigure != null && checkedFigure.getColorOfFigure() == currentColor) {
            List<Place> correctPlaces = (new ClassicChessGameValidator()).getCorrectPlacesForFigure(chessboard, checkedFigure);
            return !correctPlaces.isEmpty();
        }
        return false;
    }

    static boolean isDraw(Chessboard chessboard) {
        //TODO uprościć
        int whiteKnights = 0;
        int whiteBishops = 0;
        int blackKnights = 0;
        int blackBishops = 0;

        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                Figure figure = chessboard.getFigureByCoordinates(j, i);
                if (figure != null && figure.getTypeOfFigure() != TypeOfFigure.KING) {
                    if (figure.getTypeOfFigure() == TypeOfFigure.BISHOP &&
                            figure.getColorOfFigure() == ColorOfFigure.WHITE) {
                        whiteBishops++;
                    } else if (figure.getTypeOfFigure() == TypeOfFigure.KNIGHT &&
                            figure.getColorOfFigure() == ColorOfFigure.WHITE) {
                        whiteKnights++;
                    } else if (figure.getTypeOfFigure() == TypeOfFigure.BISHOP &&
                            figure.getColorOfFigure() == ColorOfFigure.BLACK) {
                        blackBishops++;
                    } else if (figure.getTypeOfFigure() == TypeOfFigure.KNIGHT &&
                            figure.getColorOfFigure() == ColorOfFigure.BLACK) {
                        blackKnights++;
                    } else {
                        return false;
                    }
                }
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
