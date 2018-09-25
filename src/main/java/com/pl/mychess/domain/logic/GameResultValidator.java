package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.*;
import com.pl.mychess.domain.model.match.StateOfMatch;

import java.util.List;

public class GameResultValidator {
    public static StateOfMatch getTheGameResult(Chessboard chessboard, ColorOfFigure currentColor){
        Figure checkedKing = null;
        for (Figure f : chessboard.getFigures()) {
            if (f.equals(new Figure(TypeOfFigure.KING, currentColor))) {
                checkedKing = f;
                break;
            }
        }
        boolean isTheKingAttacked = isTheFigureAttacked(chessboard, checkedKing);
        boolean hasAnyCorrectMove = hasTheCurrentPlayerAnyCorrectMove(chessboard, currentColor);
        boolean isAnotherDrawSituation = isDraw(chessboard);

        if(isTheKingAttacked){
            if(hasAnyCorrectMove){
                return StateOfMatch.CHECKMATE;
            }
            return StateOfMatch.CHECK;
        }
        if(hasAnyCorrectMove || isAnotherDrawSituation){
            return StateOfMatch.DRAW;
        }

        return StateOfMatch.GAME_IS_NOT_COMPLETED;
    }

    private static boolean isTheFigureAttacked(Chessboard chessboard, Figure checkedFigure) {
        Place placeOfCheckedFigure = chessboard.getPlaceForGivenFigure(checkedFigure);
        ColorOfFigure currentColor = checkedFigure.getColorOfFigure();

        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (isOpponentFigureAttackTheGivenFigure(chessboard, placeOfCheckedFigure, currentColor, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean isOpponentFigureAttackTheGivenFigure(Chessboard chessboard, Place placeOfCheckedFigure, ColorOfFigure colorOfFigure, int i, char j) {
        Figure opponentFigure = chessboard.getFigureByCoordinates(j, i);
        if (opponentFigure == null)
            return false;
        if (opponentFigure.getColorOfFigure() != colorOfFigure) {
            List<Place> placesAttackedByOpponentFigure = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, opponentFigure);
            if (placesAttackedByOpponentFigure.contains(placeOfCheckedFigure)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasTheCurrentPlayerAnyCorrectMove(Chessboard chessboard, ColorOfFigure currentColor) {

        return false;
    }

    private static boolean isDraw(Chessboard chessboard) {

        return false;
    }
}
