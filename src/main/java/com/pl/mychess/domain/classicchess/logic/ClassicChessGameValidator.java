package com.pl.mychess.domain.classicchess.logic;

import com.pl.mychess.domain.classicchess.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.Move;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;
import com.pl.mychess.domain.port.game.GameValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassicChessGameValidator implements GameValidator {
    @Override
    public MatchResult getTheGameResult(Chessboard chessboard, Color colorOfCheckedPlayer) {
        Place placeOfCheckedKing = StateOfGameToolsValidator.findTheKingPlace(chessboard, colorOfCheckedPlayer);
        boolean isTheKingAttacked = StateOfGameToolsValidator.isTheFigureAttacked(chessboard, placeOfCheckedKing);
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
    public Map<Place, TypeOfCustomMove> getCorrectPlacesForFigure(Chessboard chessboard, Place placeOfTestedFigure, Move lastMove) {
        List<Place> allPossiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, placeOfTestedFigure);
        Map<Place, TypeOfCustomMove> result = new HashMap<>();

        allPossiblePlaces.forEach(p -> moveSimulator(chessboard, placeOfTestedFigure, result, p));
        customMoveSimulator(chessboard, placeOfTestedFigure, lastMove, result);

        return result;
    }

    private void customMoveSimulator(Chessboard chessboard, Place placeOfTestedFigure, Move lastMove, Map<Place, TypeOfCustomMove> result) {
        Place enPassantPlace = CustomMovesValidator.isEnPassantCorrect(chessboard, placeOfTestedFigure, lastMove);
        Place shortCastlingPlace = CustomMovesValidator.isShortCastlingCorrect(chessboard, placeOfTestedFigure);
        Place longCastlingPlace = CustomMovesValidator.isLongCastlingCorrect(chessboard, placeOfTestedFigure);

        if (enPassantPlace != null) result.put(enPassantPlace, TypeOfCustomMove.EN_PASSANT);
        if (shortCastlingPlace != null) result.put(shortCastlingPlace, TypeOfCustomMove.SHORT_CASTLE);
        if (longCastlingPlace != null) result.put(longCastlingPlace, TypeOfCustomMove.LONG_CASTLE);
    }

    private void moveSimulator(Chessboard chessboard, Place placeOfTestedFigure, Map<Place, TypeOfCustomMove> result, Place p) {
        Figure testedFigure = chessboard.getFigureByCoordinates(
                placeOfTestedFigure.getCoordinateX(), placeOfTestedFigure.getCoordinateY());

        Move simulatedMove = Move.getMoveBuilder()
                .movedFigure(testedFigure)
                .previousPlace(placeOfTestedFigure)
                .nextPlace(p)
                .build();

        Chessboard simulateChessboard = (new ClassicChessChessboardFactory()).createUpdatedChessboardByMove(chessboard, simulatedMove);

        if (!StateOfGameToolsValidator.isTheFigureAttacked(simulateChessboard,
                StateOfGameToolsValidator.findTheKingPlace(simulateChessboard, testedFigure.getColor()))) {
            if (isPawnTransform(testedFigure, p)) {
                result.put(p, TypeOfCustomMove.PAWN_TRANSFORM);
            } else
                result.put(p, TypeOfCustomMove.NORMAL);
        }
    }

    private boolean isPawnTransform(Figure testedFigure, Place p) {
        return testedFigure.getTypeOfFigure() == TypeOfFigure.PAWN &&
                ((testedFigure.getColor() == Color.WHITE && p.getCoordinateY() == 8) ||
                        (testedFigure.getColor() == Color.BLACK && p.getCoordinateY() == 1));
    }
}

