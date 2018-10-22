package com.pl.mychess.domain.classicchess.chessboard;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;

class ChessboardUpdater {
    private ChessboardUpdater() {
    }

    static void updateChessboardForEnPassant(Move move, Chessboard createdChessboard) {
        Place previousPlace = createdChessboard.getPlaceByCoordinates(move.getPreviousPlace().getCoordinateX(),
                move.getPreviousPlace().getCoordinateY());
        Place nextPlace = createdChessboard.getPlaceByCoordinates(move.getNextPlace().getCoordinateX(),
                move.getNextPlace().getCoordinateY());
        Figure movedFigure = createdChessboard.getFigureByCoordinates(move.getPreviousPlace().getCoordinateX(),
                move.getPreviousPlace().getCoordinateY());
        Place beatenFigurePlace = createdChessboard.getPlaceByCoordinates(nextPlace.getCoordinateX(), previousPlace.getCoordinateY());
        Figure beatenFigure = createdChessboard.getFigureByCoordinates(beatenFigurePlace.getCoordinateX(),
                beatenFigurePlace.getCoordinateY());

        previousPlace.setCurrentFigure(null);
        nextPlace.setCurrentFigure(movedFigure);
        beatenFigurePlace.setCurrentFigure(null);
        beatenFigure.setBeaten(true);
    }

    static void updateChessboardForCastling(Move move, Chessboard createdChessboard, char prevXForRook, char nextXForRook) {
        Place previousKingPlace = createdChessboard.getPlaceByCoordinates(
                move.getPreviousPlace().getCoordinateX(), move.getPreviousPlace().getCoordinateY());
        Place nextKingPlace = createdChessboard.getPlaceByCoordinates(
                move.getNextPlace().getCoordinateX(), move.getNextPlace().getCoordinateY());
        Figure movedKing = createdChessboard.getFigureByCoordinates(
                move.getPreviousPlace().getCoordinateX(), move.getPreviousPlace().getCoordinateY());

        Place previousRookPlace = createdChessboard.getPlaceByCoordinates(prevXForRook, move.getPreviousPlace().getCoordinateY());
        Place nextRookPlace = createdChessboard.getPlaceByCoordinates(nextXForRook, move.getNextPlace().getCoordinateY());
        Figure movedRook = createdChessboard.getFigureByCoordinates(prevXForRook, move.getNextPlace().getCoordinateY());

        previousKingPlace.setCurrentFigure(null);
        nextKingPlace.setCurrentFigure(movedKing);
        previousRookPlace.setCurrentFigure(null);
        nextRookPlace.setCurrentFigure(movedRook);
        movedKing.setMoved(true);
        movedRook.setMoved(true);
    }

    static void updateChessboardForNormalMove(Move move, Chessboard createdChessboard) {
        Place previousPlace = createdChessboard.getPlaceByCoordinates(move.getPreviousPlace().getCoordinateX(),
                move.getPreviousPlace().getCoordinateY());
        Figure movedFigure = createdChessboard.getFigureByCoordinates(move.getPreviousPlace().getCoordinateX(),
                move.getPreviousPlace().getCoordinateY());
        Place nextPlace = createdChessboard.getPlaceByCoordinates(move.getNextPlace().getCoordinateX(),
                move.getNextPlace().getCoordinateY());
        Figure beatenFigure = createdChessboard.getFigureByCoordinates(move.getNextPlace().getCoordinateX(),
                move.getNextPlace().getCoordinateY());

        previousPlace.setCurrentFigure(null);
        nextPlace.setCurrentFigure(movedFigure);
        movedFigure.setMoved(true);
        if (beatenFigure != null) beatenFigure.setBeaten(true);
        if (TypeOfCustomMove.PAWN_TRANSFORM.equals(move.getTypeOfCustomMove()))
            movedFigure.setTypeOfFigure(move.getPawnTransformNewFigure());

    }
}
