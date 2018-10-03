package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.port.ChessboardCreator;

import java.util.ArrayList;
import java.util.List;

public class ClassicChessChessboardFactory implements ChessboardCreator {
    public Chessboard createEmptyChessboard() {
        Place[][] placesOfChessboard = new Place[8][8];
        List<Figure> figuresList = new ArrayList<>();

        for (int i = 0; i < placesOfChessboard.length; i++) {
            for (int j = 0; j < placesOfChessboard[i].length; j++) {
                char coordinateX = (char) ((int) 'a' + j);
                placesOfChessboard[i][j] = new Place(coordinateX, i + 1);
            }
        }
        return new Chessboard(placesOfChessboard, figuresList);
    }

    public Chessboard createInitialChessboard() {
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

    public Chessboard createUpdatedChessboardByMove(Chessboard chessboard, Move move) {
        //TODO zrobić exception na wypadek niedopasowania move z chessboard
        //
        Chessboard createdChessboard = createCopyOfChessboard(chessboard);
        switch (move.getTypeOfCustomMove()) {
            case NORMAL:
                ChessboardUpdater.updateChessboardForNormalMove(move, createdChessboard);
                break;
            case SHORT_CASTLE:
                ChessboardUpdater.updateChessboardForCastling(move, createdChessboard, 'h', 'f');
                break;
            case LONG_CASTLE:
                ChessboardUpdater.updateChessboardForCastling(move, createdChessboard, 'a', 'd');
                break;
            case EN_PASSANT:
                ChessboardUpdater.updateChessboardForEnPassant(move, createdChessboard);
                break;
            case PAWN_TRANSFORM:
                ChessboardUpdater.updateChessboardForNormalMove(move, createdChessboard);
                break;
            default:
        }
        return createdChessboard;
    }

    //TODO zrobić cofanie ruchu
    @Override
    public Chessboard createUpdatedChessboardByBackMove(Chessboard chessboard, Move move) {
        return null;
    }

    private Chessboard createCopyOfChessboard(Chessboard chessboard) {
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
}
