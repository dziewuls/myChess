package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.classicchess.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;

class CustomMovesValidator {
    private CustomMovesValidator() {
    }

    static Place isShortCastlingCorrect(Chessboard chessboard, Place placeOfTestedFigure) {
        return checkConditionsForCastling(chessboard, placeOfTestedFigure, TypeOfCustomMove.SHORT_CASTLE);
    }

    static Place isLongCastlingCorrect(Chessboard chessboard, Place placeOfTestedFigure) {
        return checkConditionsForCastling(chessboard, placeOfTestedFigure, TypeOfCustomMove.LONG_CASTLE);
    }

    static Place isEnPassantCorrect(Chessboard chessboard, Place placeOfTestedFigure, Move lastMove) {
        Figure testedFigure = chessboard.getFigureByCoordinates(placeOfTestedFigure.getCoordinateX(), placeOfTestedFigure.getCoordinateY());

        if (testedFigure == null)
            return null;
        if(lastMove == null)
            return null;

        int currentCorY = 5;
        int nextCorY = 6;
        int opponentPrevCorY = 7;
        if (testedFigure.getColor() == Color.BLACK) {
            currentCorY = 4;
            nextCorY = 3;
            opponentPrevCorY = 2;
        }

        Place placeOfLastMovedFigure = lastMove.getNextPlace();
        Place previousPlaceOfLastMovedFigure = lastMove.getPreviousPlace();
        Place nextPlaceOfTestedFigure = chessboard.getPlaceByCoordinates(placeOfLastMovedFigure.getCoordinateX(), nextCorY);
        Figure lastMovedFigure = lastMove.getMovedFigure();

        if (testedFigure.getTypeOfFigure() != TypeOfFigure.PAWN ||
                placeOfTestedFigure.getCoordinateY() != currentCorY ||
                (placeOfTestedFigure.getCoordinateX() + 1 != placeOfLastMovedFigure.getCoordinateX() &&
                        placeOfTestedFigure.getCoordinateX() - 1 != placeOfLastMovedFigure.getCoordinateX()) ||
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

    private static Place checkConditionsForCastling(Chessboard chessboard, Place placeOfTestedFigure, TypeOfCustomMove typeOfCastling) {
        char corX = placeOfTestedFigure.getCoordinateX();
        int corY = placeOfTestedFigure.getCoordinateY();
        Figure testedFigure = chessboard.getFigureByCoordinates(corX, corY);

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

        if (isTheCastlingCorrect(chessboard, testedFigure, placeOfTestedFigure,
                placeOfPassageTroughTheKing, newPlaceOccupiedByKing, rookForCastling)) {
            return newPlaceOccupiedByKing;
        } else return null;
    }

    private static boolean isTheCastlingCorrect(Chessboard chessboard, Figure testedFigure, Place placeOfTestedFigure,
                                                Place placeOfPassageTroughTheKing, Place newPlaceOccupiedByKing, Figure rookForCastling) {
        boolean b1 = testedFigure.getTypeOfFigure() == TypeOfFigure.KING;
        boolean b2 = rookForCastling.getTypeOfFigure() == TypeOfFigure.ROOK;
        boolean b3 = rookForCastling.getColor() == testedFigure.getColor();
        boolean b4 = !testedFigure.isMoved();
        boolean b5 = !rookForCastling.isMoved();
        boolean b6 = !StateOfGameToolsValidator.isTheFigureAttacked(chessboard, placeOfTestedFigure);
        boolean b7 = newPlaceOccupiedByKing.getCurrentFigure() == null;
        boolean b8 = placeOfPassageTroughTheKing.getCurrentFigure() == null;
        boolean b9 = !StateOfGameToolsValidator.isThePlaceAttacked(
                chessboard, placeOfPassageTroughTheKing, testedFigure.getColor());
        boolean b10 = !StateOfGameToolsValidator.isThePlaceAttacked(
                chessboard, newPlaceOccupiedByKing, testedFigure.getColor());
        return b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10;
    }

    private static boolean isEnpassantNotDiscoverCheck(Chessboard chessboard, Figure testedFigure, Place placeOfTestedFigure,
                                                       Place nextPlaceOfTestedFigure, Figure lastMovedFigure) {
        Move simulatedMove = Move.getMoveBuilder()
                .movedFigure(testedFigure)
                .previousPlace(placeOfTestedFigure)
                .nextPlace(nextPlaceOfTestedFigure)
                .beatenFigure(lastMovedFigure)
                .typeOfCustomMove(TypeOfCustomMove.EN_PASSANT)
                .build();

        Chessboard simulateChessboard = (new ClassicChessChessboardFactory()).createUpdatedChessboardByMove(chessboard, simulatedMove);

        return !StateOfGameToolsValidator.isTheFigureAttacked(simulateChessboard,
                StateOfGameToolsValidator.findTheKingPlace(simulateChessboard, testedFigure.getColor()));
    }
}
