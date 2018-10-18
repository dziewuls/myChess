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

import java.util.HashMap;
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
    public void makeMove(Place chosenPlace, TypeOfCustomMove typeOfCustomMove, TypeOfFigure typeOfPawnTransformFigure) {
        Figure beatenFigure = currentChessboard.getFigureByCoordinates(
                chosenPlace.getCoordinateX(), chosenPlace.getCoordinateY());

        Move newMove = moveBuilder
                .nextPlace(chosenPlace)
                .beatenFigure(beatenFigure)
                .pawnTransformNewFigure(typeOfPawnTransformFigure)
                .typeOfCustomMove(typeOfCustomMove)
                .build();

        currentChessboard = chessboardCreator.createUpdatedChessboardByMove(currentChessboard, newMove);
        matchStatus.newMove(newMove);
    }

    @Override
    public void backMove() {
        Move lastMove = matchStatus.getLastMove();
        currentChessboard = chessboardCreator.createUpdatedChessboardByBackMove(currentChessboard, lastMove);
        matchStatus.backMove();
    }

    @Override
    public Map<Place, TypeOfCustomMove> getCorrectMoveOptions(Place chosenPlace) {
        Place previousPlace = currentChessboard.getPlaceByCoordinates(
                chosenPlace.getCoordinateX(), chosenPlace.getCoordinateY());
        Figure movedFigure = currentChessboard.getFigureByCoordinates(
                chosenPlace.getCoordinateX(), chosenPlace.getCoordinateY());

        if(movedFigure == null) return new HashMap<>();
        if(movedFigure.getColor() != matchStatus.getCurrentPlayerColor()) return new HashMap<>();

        moveBuilder
                .previousPlace(previousPlace)
                .movedFigure(movedFigure);

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
