package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.port.GameValidator;

import java.util.ArrayList;
import java.util.List;

public class ClassicChessGameValidator implements GameValidator {
    @Override
    public MatchResult getTheGameResult(Chessboard chessboard, Color colorOfCheckedPlayer) {
        Figure checkedKing = StateOfGameToolsValidator.findTheKing(chessboard, colorOfCheckedPlayer);
        boolean isTheKingAttacked = StateOfGameToolsValidator.isTheFigureAttacked(chessboard, checkedKing);
        boolean hasAnyCorrectMove = StateOfGameToolsValidator.hasTheCurrentPlayerAnyCorrectMove(chessboard, colorOfCheckedPlayer);
        boolean isAnotherDrawSituation = StateOfGameToolsValidator.isInsufficientMaterialForMate(chessboard);

        if (isTheKingAttacked) {
            if (!hasAnyCorrectMove) {
                return (colorOfCheckedPlayer == Color.BLACK) ?
                        MatchResult.WHITE_IS_A_WINNER : MatchResult.BLACK_IS_A_WINNER;
            }
            return MatchResult.CHECK;
        }
        if (!hasAnyCorrectMove || isAnotherDrawSituation) {
            return MatchResult.DRAW;
        }
        return MatchResult.GAME_IS_NOT_COMPLETED;
    }

    @Override
    public List<Place> getCorrectPlacesForFigure(Chessboard chessboard, Figure testedFigure, Move lastMove) {
        List<Place> allPossiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedFigure);
        List<Place> result = new ArrayList<>();

        allPossiblePlaces.forEach(p -> moveSimulator(chessboard, testedFigure, result, p));
        customMoveSimulator(chessboard, testedFigure, lastMove, result);

        return result;
    }

    private void customMoveSimulator(Chessboard chessboard, Figure testedFigure, Move lastMove, List<Place> result) {
        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedFigure, lastMove);
        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedFigure);
        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedFigure);

        if (enPassantPlace != null) result.add(enPassantPlace);
        if (shortCastlingPlace != null) result.add(shortCastlingPlace);
        if (longCastlingPlace != null) result.add(longCastlingPlace);
    }

    private void moveSimulator(Chessboard chessboard, Figure testedFigure, List<Place> result, Place p) {
        Move simulatedMove = Move.getMoveBuilder()
                .movedFigure(testedFigure)
                .previousPlace(chessboard.getPlaceForGivenFigure(testedFigure))
                .nextPlace(p)
                .currentPlayerColor(testedFigure.getColor())
                .build();

        Chessboard simulateChessboard = (new ClassicChessChessboardFactory()).createUpdatedChessboardByMove(chessboard, simulatedMove);

        if (!StateOfGameToolsValidator.isTheFigureAttacked(simulateChessboard,
                StateOfGameToolsValidator.findTheKing(simulateChessboard, testedFigure.getColor()))) {
            result.add(p);
        }
    }
}
