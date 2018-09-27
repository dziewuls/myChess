package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.chessboard.ClassicChessChessboardFactory;
import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.match.StateOfMatch;
import com.pl.mychess.domain.port.GameValidator;

import java.util.ArrayList;
import java.util.List;

public class ClassicChessGameValidator implements GameValidator {
    @Override
    public StateOfMatch getTheGameResult(Chessboard chessboard, ColorOfFigure currentColor) {
        Figure checkedKing = StateOfGameToolsValidator.findTheKing(chessboard, currentColor);
        boolean isTheKingAttacked = StateOfGameToolsValidator.isTheFigureAttacked(chessboard, checkedKing);
        boolean hasAnyCorrectMove = StateOfGameToolsValidator.hasTheCurrentPlayerAnyCorrectMove(chessboard, currentColor);
        boolean isAnotherDrawSituation = StateOfGameToolsValidator.isDraw(chessboard);

        if (isTheKingAttacked) {
            if (!hasAnyCorrectMove) {
                return StateOfMatch.CHECKMATE;
            }
            return StateOfMatch.CHECK;
        }
        if (!hasAnyCorrectMove || isAnotherDrawSituation) {
            return StateOfMatch.DRAW;
        }
        return StateOfMatch.GAME_IS_NOT_COMPLETED;
    }

    @Override
    public List<Place> getCorrectPlacesForFigure(Chessboard chessboard, Figure testedFigure) {
        List<Place> allPossiblePlaces = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, testedFigure);
        List<Place> result = new ArrayList<>();

        allPossiblePlaces.forEach(p -> {
            Move simulatedMove = Move.getMoveBuilder()
                    .movedFigure(testedFigure)
                    .previousPlace(chessboard.getPlaceForGivenFigure(testedFigure))
                    .nextPlace(p)
                    .currentPlayerColor(testedFigure.getColorOfFigure())
                    .build();

            Chessboard simulateChessboard = (new ClassicChessChessboardFactory()).createUpdatedChessboard(chessboard, simulatedMove);

            if (!StateOfGameToolsValidator.isTheFigureAttacked(simulateChessboard,
                    StateOfGameToolsValidator.findTheKing(simulateChessboard, testedFigure.getColorOfFigure()))) {
                result.add(p);
            }
        });
        return result;
    }
}
