package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.*;

import java.util.List;

public class GameResultValidator {
    public static boolean isCheck(Chessboard chessboard, ColorOfFigure currentColor) {
        Place placeOfCheckedKing = null;
        for (Figure f : chessboard.getFigures()) {
            if (f.equals(new Figure(TypeOfFigure.KING, currentColor))) {
                placeOfCheckedKing = chessboard.getPlaceForGivenFigure(f);
                break;
            }
        }
        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (isOpponentFigureAttackTheKing(chessboard, placeOfCheckedKing, currentColor, i, j))
                    return true;
            }
        }
        return false;
    }

    private static boolean isOpponentFigureAttackTheKing(Chessboard chessboard, Place placeOfCheckedKing, ColorOfFigure colorOfFigure, int i, char j) {
        Figure opponentFigure = chessboard.getFigureByCoordinates(j, i);
        if (opponentFigure == null)
            return false;
        if (opponentFigure.getColorOfFigure() != colorOfFigure) {
            List<Place> placesAttackedByOpponentFigure = MovesValidator.getAllPossiblePlacesForTheFigure(chessboard, opponentFigure);
            if (placesAttackedByOpponentFigure.contains(placeOfCheckedKing)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCheckmate(Chessboard chessboard, ColorOfFigure currentColor) {

        return false;
    }

    public static boolean isStalemate(Chessboard chessboard, ColorOfFigure currentColor) {

        return false;
    }

    public static boolean isDraw(Chessboard chessboard) {

        return false;
    }
}
