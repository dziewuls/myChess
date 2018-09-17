package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.*;

import java.util.ArrayList;
import java.util.List;

public class ChessboardFactory {
    public static Chessboard createCopyOfChessboard(Chessboard chessboard) {
        Place[][] placesOfChessboard = new Place[8][8];
        List<Figure> figuresList = new ArrayList<>();

        for (int i = 0; i < placesOfChessboard.length; i++) {
            for (int j = 0; j < placesOfChessboard[i].length; j++) {
                char coordinateX = (char) ((int) 'a' + j);
                Figure figureForCopy = chessboard.getFigureByCoordinates(coordinateX, i + 1);
                Figure createdFigure = FigureFactory.createCopyOfFigure(figureForCopy);
                placesOfChessboard[i][j] = new Place(coordinateX, i + 1, createdFigure);
                if (createdFigure != null) figuresList.add(createdFigure);
            }
        }
        return new Chessboard(placesOfChessboard, figuresList);
    }

    public static Chessboard createChessboard() {
        Place[][] placesOfChessboard = new Place[8][8];
        List<Figure> figuresList = new ArrayList<>();

        for (int i = 0; i < placesOfChessboard.length; i++) {
            for (int j = 0; j < placesOfChessboard[i].length; j++) {
                char coordinateX = (char) ((int) 'a' + j);
                Figure createdFigure = FigureFactory.createFigureByStartedPosition(coordinateX, i + 1);
                placesOfChessboard[i][j] = new Place(coordinateX, i + 1, createdFigure);
                if (createdFigure != null) figuresList.add(createdFigure);
            }
        }

        return new Chessboard(placesOfChessboard, figuresList);
    }

    public static Chessboard createChessboard(Chessboard chessboard, List<Move> moves) {
        Chessboard createdChessboard = chessboard;
        for (Move move : moves) {
            createdChessboard = createChessboard(createdChessboard, move);
        }
        return createdChessboard;
    }

    public static Chessboard createChessboard(Chessboard chessboard, Move move) {
        Chessboard createdChessboard = createCopyOfChessboard(chessboard);
        switch (move.getTypeOfCustomMove()) {
            case NORMAL:
                updateChessboardForNormalMove(move, createdChessboard);
                break;
            case SHORT_CASTLE:
                updateChessboardForCastle(move, createdChessboard, 'g', 'h', 'f');
                break;
            case LONG_CASTLE:
                updateChessboardForCastle(move, createdChessboard, 'c', 'a', 'd');
                break;
            case EN_PASSANT:
                updateChessboardForEnPassant(move, createdChessboard);
                break;
            case PAWN_TRANSFORM:
                updateChessboardForNormalMove(move, createdChessboard);
                break;
            default:
        }
        return createdChessboard;
    }

    private static void updateChessboardForEnPassant(Move move, Chessboard createdChessboard) {
        Place previousPlace = createdChessboard.getPlaceByCoordinates(move.getPreviousPlace().getCoordinateX(),
                move.getPreviousPlace().getCoordinateY());
        Place nextPlace = createdChessboard.getPlaceByCoordinates(move.getNextPlace().getCoordinateX(),
                move.getNextPlace().getCoordinateY());
        Figure movedFigure = createdChessboard.getFigureByCoordinates(move.getPreviousPlace().getCoordinateX(),
                move.getPreviousPlace().getCoordinateY());
        Place beatenFigurePlace = (ColorOfFigure.WHITE.equals(movedFigure.getColorOfFigure())) ?
                createdChessboard.getPlaceByCoordinates(move.getNextPlace().getCoordinateX(),
                        move.getNextPlace().getCoordinateY() - 1) :
                createdChessboard.getPlaceByCoordinates(move.getNextPlace().getCoordinateX(),
                        move.getNextPlace().getCoordinateY() + 1);
        Figure beatenFigure = createdChessboard.getFigureByCoordinates(beatenFigurePlace.getCoordinateX(),
                beatenFigurePlace.getCoordinateY());

        previousPlace.setCurrentFigure(null);
        nextPlace.setCurrentFigure(movedFigure);
        beatenFigurePlace.setCurrentFigure(null);
        beatenFigure.setBeaten(true);
    }

    private static void updateChessboardForCastle(Move move, Chessboard createdChessboard,
                                                  char nextXForKing, char prevXForRook, char nextXForRook) {
        Place previousKingPlace = createdChessboard.getPlaceByCoordinates('e', move.getNextPlace().getCoordinateY());
        Place nextKingPlace = createdChessboard.getPlaceByCoordinates(nextXForKing, move.getNextPlace().getCoordinateY());
        Place previousRookPlace = createdChessboard.getPlaceByCoordinates(prevXForRook, move.getNextPlace().getCoordinateY());
        Place nextRookPlace = createdChessboard.getPlaceByCoordinates(nextXForRook, move.getNextPlace().getCoordinateY());
        Figure movedKing = createdChessboard.getFigureByCoordinates('e', move.getNextPlace().getCoordinateY());
        Figure movedRook = createdChessboard.getFigureByCoordinates(prevXForRook, move.getNextPlace().getCoordinateY());

        previousKingPlace.setCurrentFigure(null);
        nextKingPlace.setCurrentFigure(movedKing);
        previousRookPlace.setCurrentFigure(null);
        nextRookPlace.setCurrentFigure(movedRook);
        movedKing.setMoved(true);
        movedRook.setMoved(true);
    }

    private static void updateChessboardForNormalMove(Move move, Chessboard createdChessboard) {
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
