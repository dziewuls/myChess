package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.*;

class CustomMovesValidator {
    private CustomMovesValidator() {
    }

    static boolean isShortCastlingCorrect(Chessboard chessboard, Figure testedFigure) {
        return checkConditionsForCastling(chessboard, testedFigure, TypeOfCustomMove.SHORT_CASTLE);
    }

    static boolean isLongCastlingCorrect(Chessboard chessboard, Figure testedFigure) {
        return checkConditionsForCastling(chessboard, testedFigure, TypeOfCustomMove.LONG_CASTLE);

    }

    private static boolean checkConditionsForCastling(Chessboard chessboard, Figure testedFigure, TypeOfCustomMove typeOfCastling) {
        Place currentPlaceOfTestedFigure = chessboard.getPlaceForGivenFigure(testedFigure);
        char corX = currentPlaceOfTestedFigure.getCoordinateX();
        int corY = currentPlaceOfTestedFigure.getCoordinateY();

        int tmp1 = 1;
        int tmp2 = 2;
        int tmp3 = 3;
        if (typeOfCastling == TypeOfCustomMove.LONG_CASTLE) {
            tmp1 = -1;
            tmp2 = -2;
            tmp3 = -4;
        }

        if (!StateOfGameToolsValidator.isThePlaceExist((char) (corX + tmp1), corY) ||
                !StateOfGameToolsValidator.isThePlaceExist((char) (corX + tmp2), corY) ||
                !StateOfGameToolsValidator.isThePlaceExist((char) (corX + tmp3), corY)) {
            return false;
        }

        Place placeOfPassageTroughTheKing = chessboard.getPlaceByCoordinates((char) (corX + tmp1), corY);
        Place newPlaceOccupiedByKing = chessboard.getPlaceByCoordinates((char) (corX + tmp2), corY);
        Figure rookForCastling = chessboard.getFigureByCoordinates((char) (corX + tmp3), corY);

        if (testedFigure == null || rookForCastling == null)
            return false;

        return testedFigure.getTypeOfFigure() == TypeOfFigure.KING &&
                rookForCastling.getTypeOfFigure() == TypeOfFigure.ROOK &&
                rookForCastling.getColorOfFigure() == testedFigure.getColorOfFigure() &&
                !testedFigure.isMoved() &&
                !rookForCastling.isMoved() &&
                StateOfGameToolsValidator.isTheFigureAttacked(chessboard, testedFigure) &&
                newPlaceOccupiedByKing.getCurrentFigure() == null &&
                placeOfPassageTroughTheKing.getCurrentFigure() == null &&
                StateOfGameToolsValidator.isThePlaceAttacked(
                        chessboard, placeOfPassageTroughTheKing, testedFigure.getColorOfFigure()) &&
                StateOfGameToolsValidator.isThePlaceAttacked(
                        chessboard, newPlaceOccupiedByKing, testedFigure.getColorOfFigure());
    }

    static boolean isEnPassantCorrect(Chessboard chessboard, Figure testedFigure, Move lastMove) {
        //TODO zapełnić
        return false;
    }
}
