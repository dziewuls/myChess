package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;

class CustomMovesValidator {
    private CustomMovesValidator() {
    }

    static Place isShortCastlingCorrect(Chessboard chessboard, Figure testedFigure) {
        return checkConditionsForCastling(chessboard, testedFigure, TypeOfCustomMove.SHORT_CASTLE);
    }

    static Place isLongCastlingCorrect(Chessboard chessboard, Figure testedFigure) {
        return checkConditionsForCastling(chessboard, testedFigure, TypeOfCustomMove.LONG_CASTLE);
    }

    private static Place checkConditionsForCastling(Chessboard chessboard, Figure testedFigure, TypeOfCustomMove typeOfCastling) {
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
            return null;
        }

        Place placeOfPassageTroughTheKing = chessboard.getPlaceByCoordinates((char) (corX + tmp1), corY);
        Place newPlaceOccupiedByKing = chessboard.getPlaceByCoordinates((char) (corX + tmp2), corY);
        Figure rookForCastling = chessboard.getFigureByCoordinates((char) (corX + tmp3), corY);

        if (testedFigure == null || rookForCastling == null)
            return null;

        if (isTheCastlingCorrect(chessboard, testedFigure, placeOfPassageTroughTheKing, newPlaceOccupiedByKing, rookForCastling)) {
            return newPlaceOccupiedByKing;
        } else return null;
    }

    private static boolean isTheCastlingCorrect(Chessboard chessboard, Figure testedFigure, Place placeOfPassageTroughTheKing, Place newPlaceOccupiedByKing, Figure rookForCastling) {
        boolean b1 = testedFigure.getTypeOfFigure() == TypeOfFigure.KING;
        boolean b2 = rookForCastling.getTypeOfFigure() == TypeOfFigure.ROOK;
        boolean b3 = rookForCastling.getColorOfFigure() == testedFigure.getColorOfFigure();
        boolean b4 = !testedFigure.isMoved();
        boolean b5 = !rookForCastling.isMoved();
        boolean b6 = !StateOfGameToolsValidator.isTheFigureAttacked(chessboard, testedFigure);
        boolean b7 = newPlaceOccupiedByKing.getCurrentFigure() == null;
        boolean b8 = placeOfPassageTroughTheKing.getCurrentFigure() == null;
        boolean b9 = !StateOfGameToolsValidator.isThePlaceAttacked(
                chessboard, placeOfPassageTroughTheKing, testedFigure.getColorOfFigure());
        boolean b10 = !StateOfGameToolsValidator.isThePlaceAttacked(
                chessboard, newPlaceOccupiedByKing, testedFigure.getColorOfFigure());
        return b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10;
    }

    static Place isEnPassantCorrect(Chessboard chessboard, Figure testedFigure, Move lastMove) {
        if (testedFigure == null)
            return null;

        int currentCorY = 5;
        int nextCorY = 6;
        int opponentPrevCorY = 7;
        if (testedFigure.getColorOfFigure() == ColorOfFigure.BLACK) {
            currentCorY = 4;
            nextCorY = 3;
            opponentPrevCorY = 2;
        }

        Place placeOfTestedFigure = chessboard.getPlaceForGivenFigure(testedFigure);
        Place placeOfLastMovedFigure = lastMove.getNextPlace();
        Place previousPlaceOfLastMovedFigure = lastMove.getPreviousPlace();
        Place nextPlaceOfTestedFigure = chessboard.getPlaceByCoordinates(placeOfLastMovedFigure.getCoordinateX(), nextCorY);
        Figure lastMovedFigure = lastMove.getMovedFigure();

        if (testedFigure.getTypeOfFigure() != TypeOfFigure.PAWN ||
                placeOfTestedFigure.getCoordinateY() != currentCorY ||
                lastMovedFigure.getTypeOfFigure() != TypeOfFigure.PAWN ||
                placeOfLastMovedFigure.getCoordinateY() != currentCorY ||
                previousPlaceOfLastMovedFigure.getCoordinateY() != opponentPrevCorY) {
            return null;
        }

        if (isEnpassantNotDiscoverCheck(chessboard, testedFigure, placeOfTestedFigure, nextPlaceOfTestedFigure, lastMovedFigure)) {
            return nextPlaceOfTestedFigure;
        } else {
            return null;
        }
    }

    private static boolean isEnpassantNotDiscoverCheck(Chessboard chessboard, Figure testedFigure, Place placeOfTestedFigure,
                                                       Place nextPlaceOfTestedFigure, Figure lastMovedFigure) {
        Move simulatedMove = Move.getMoveBuilder()
                .movedFigure(testedFigure)
                .previousPlace(placeOfTestedFigure)
                .nextPlace(nextPlaceOfTestedFigure)
                .currentPlayerColor(testedFigure.getColorOfFigure())
                .beatenFigure(lastMovedFigure)
                .typeOfCustomMove(TypeOfCustomMove.EN_PASSANT)
                .build();

        Chessboard simulateChessboard = (new ClassicChessChessboardFactory()).createUpdatedChessboard(chessboard, simulatedMove);

        return !StateOfGameToolsValidator.isTheFigureAttacked(simulateChessboard,
                StateOfGameToolsValidator.findTheKing(simulateChessboard, testedFigure.getColorOfFigure()));
    }
}
