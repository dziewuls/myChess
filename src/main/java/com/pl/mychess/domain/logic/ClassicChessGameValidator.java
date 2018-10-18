package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import com.pl.mychess.domain.port.GameValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Place, TypeOfCustomMove> getCorrectPlacesForFigure(Chessboard chessboard, Figure testedFigure, Move lastMove) {
        List<Place> allPossiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedFigure);
        Map<Place, TypeOfCustomMove> result = new HashMap<>();

        allPossiblePlaces.forEach(p -> moveSimulator(chessboard, testedFigure, result, p));
        customMoveSimulator(chessboard, testedFigure, lastMove, result);

        return result;
    }

    private void customMoveSimulator(Chessboard chessboard, Figure testedFigure, Move lastMove, Map<Place, TypeOfCustomMove> result) {
        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, testedFigure, lastMove);
        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, testedFigure);
        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, testedFigure);

        if (enPassantPlace != null) result.put(enPassantPlace, TypeOfCustomMove.EN_PASSANT);
        if (shortCastlingPlace != null) result.put(shortCastlingPlace, TypeOfCustomMove.SHORT_CASTLE);
        if (longCastlingPlace != null) result.put(longCastlingPlace, TypeOfCustomMove.LONG_CASTLE);
    }

    private void moveSimulator(Chessboard chessboard, Figure testedFigure, Map<Place, TypeOfCustomMove> result, Place p) {
        Move simulatedMove = Move.getMoveBuilder()
                .movedFigure(testedFigure)
                .previousPlace(chessboard.getPlaceForGivenFigure(testedFigure))
                .nextPlace(p)
                .currentPlayerColor(testedFigure.getColor())
                .build();

        Chessboard simulateChessboard = (new ClassicChessChessboardFactory()).createUpdatedChessboardByMove(chessboard, simulatedMove);

        if (!StateOfGameToolsValidator.isTheFigureAttacked(simulateChessboard,
                StateOfGameToolsValidator.findTheKing(simulateChessboard, testedFigure.getColor()))) {
            if (isPawnTransform(testedFigure, p)) {
                result.put(p, TypeOfCustomMove.PAWN_TRANSFORM);
            }
        } else
            result.put(p, TypeOfCustomMove.NORMAL);
    }

    private boolean isPawnTransform(Figure testedFigure, Place p) {
        return testedFigure.getTypeOfFigure() == TypeOfFigure.PAWN &&
                ((testedFigure.getColor() == Color.WHITE && p.getCoordinateY() == 8) ||
                        (testedFigure.getColor() == Color.BLACK && p.getCoordinateY() == 1));
    }
}

