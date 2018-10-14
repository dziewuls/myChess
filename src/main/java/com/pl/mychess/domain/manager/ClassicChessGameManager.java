package com.pl.mychess.domain.manager;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.logic.ClassicChessGameValidator;
import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Color;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.player.Player;
import com.pl.mychess.domain.model.state.MatchStatus;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.port.ChessboardCreator;
import com.pl.mychess.domain.port.GameManager;
import com.pl.mychess.domain.port.GameValidator;

import java.util.List;

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
    public void makeMove(String chosenPlace) {
    }

    @Override
    public void backMove() {
    }

    @Override
    public List<Place> getCorrectMoveOptions(String chosenPlace) {
        Place previousPlace = currentChessboard.getPlaceByCoordinates(
                chosenPlace.charAt(0), Character.getNumericValue(chosenPlace.charAt(1)));
        Figure movedFigure = currentChessboard.getFigureByCoordinates(
                chosenPlace.charAt(0), Character.getNumericValue(chosenPlace.charAt(1)));
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
