package com.pl.mychess.domain.manager;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.logic.ClassicChessGameValidator;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.player.Player;
import com.pl.mychess.domain.model.state.MatchStatus;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import com.pl.mychess.domain.port.ChessboardCreator;
import com.pl.mychess.domain.port.GameManager;
import com.pl.mychess.domain.port.GameValidator;

import java.util.List;
import java.util.Map;

public class ClassicChessGameManager implements GameManager {
    private ChessboardCreator chessboardCreator;
    private GameValidator gameValidator;
    private Chessboard currentChessboard;
    private MatchStatus matchStatus;
    private Move.MoveBuilder moveBuilder;

    public ClassicChessGameManager(Player whitePlayer, Player blackPlayer) {
        this.chessboardCreator = new ClassicChessChessboardFactory();
        this.gameValidator = new ClassicChessGameValidator();
        this.currentChessboard = chessboardCreator.createInitialChessboard();
        this.matchStatus = new MatchStatus(whitePlayer, blackPlayer);
        this.moveBuilder = Move.getMoveBuilder();
    }

    @Override
    public void makeMove(Place chosenPlace, TypeOfCustomMove typeOfCustomMove) {
        Figure beatenFigure = currentChessboard.getFigureByCoordinates(
                chosenPlace.getCoordinateX(), chosenPlace.getCoordinateY());
//TODO zbudowac i wykonac ruch

        Move newMove = moveBuilder.nextPlace(chosenPlace)
                .beatenFigure(beatenFigure)
                //.pawnTransformNewFigure()
                .typeOfCustomMove(typeOfCustomMove)
                .build();
    }

    @Override
    public void backMove() {
    }

    @Override
    public Map<Place, TypeOfCustomMove> getCorrectMoveOptions(Place chosenPlace) {
        Place previousPlace = currentChessboard.getPlaceByCoordinates(
                chosenPlace.getCoordinateX(), chosenPlace.getCoordinateY());
        Figure movedFigure = currentChessboard.getFigureByCoordinates(
                chosenPlace.getCoordinateX(), chosenPlace.getCoordinateY());
        moveBuilder.previousPlace(previousPlace)
                .movedFigure(movedFigure)
                .currentPlayerColor(matchStatus.getCurrentPlayerColor());

        return gameValidator.getCorrectPlacesForFigure(currentChessboard, movedFigure, matchStatus.getLastMove());
    }

    @Override
    public List<String> getMovesHistory() {
        return matchStatus.getHistoryOfMovesInString();
    }

    @Override
    public MatchResult getGameResult() {
        return gameValidator.getTheGameResult(currentChessboard, matchStatus.getCurrentPlayerColor());
    }

    @Override
    public Chessboard getCurrentChessboard() {
        return currentChessboard;
    }
}
