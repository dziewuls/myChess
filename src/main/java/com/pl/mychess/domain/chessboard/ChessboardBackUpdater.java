package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;

class ChessboardBackUpdater {
    private ChessboardBackUpdater() {
    }

    static void updateChessboardForEnPassant(Move move, Chessboard createdChessboard) {
        Place previousPlace = move.getPreviousPlace();
        Place nextPlace = move.getNextPlace();
        Figure movedFigure = move.getMovedFigure();
        Figure beatenFigure = move.getBeatenFigure();
        Place beatenFigurePlace = createdChessboard.getPlaceByCoordinates(nextPlace.getCoordinateX(), previousPlace.getCoordinateY());

        previousPlace.setCurrentFigure(movedFigure);
        nextPlace.setCurrentFigure(null);
        beatenFigurePlace.setCurrentFigure(beatenFigure);
        beatenFigure.setBeaten(false);
    }

    static void updateChessboardForCastling(Move move, Chessboard createdChessboard, char prevXForRook, char nextXForRook) {
        Place previousKingPlace = move.getPreviousPlace();
        Place nextKingPlace = move.getNextPlace();
        Figure movedKing = move.getMovedFigure();

        Place previousRookPlace = createdChessboard.getPlaceByCoordinates(prevXForRook, move.getPreviousPlace().getCoordinateY());
        Place nextRookPlace = createdChessboard.getPlaceByCoordinates(nextXForRook, move.getNextPlace().getCoordinateY());
        Figure movedRook = createdChessboard.getFigureByCoordinates(nextXForRook, move.getNextPlace().getCoordinateY());

        previousKingPlace.setCurrentFigure(movedKing);
        nextKingPlace.setCurrentFigure(null);
        previousRookPlace.setCurrentFigure(movedRook);
        nextRookPlace.setCurrentFigure(null);
        movedKing.setMoved(false);
        movedRook.setMoved(false);
    }

    static void updateChessboardForNormalMove(Move move, Chessboard createdChessboard) {
        Place previousPlace = move.getPreviousPlace();
        Figure movedFigure = move.getMovedFigure();
        Place nextPlace = move.getNextPlace();
        Figure beatenFigure = move.getBeatenFigure();

        previousPlace.setCurrentFigure(movedFigure);
        nextPlace.setCurrentFigure(beatenFigure);
        movedFigure.setMoved(false);
        if (beatenFigure != null) beatenFigure.setBeaten(false);
        if (TypeOfCustomMove.PAWN_TRANSFORM.equals(move.getTypeOfCustomMove()))
            movedFigure.setTypeOfFigure(TypeOfFigure.PAWN);

    }
}
