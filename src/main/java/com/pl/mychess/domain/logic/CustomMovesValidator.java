package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Move;

public class CustomMovesValidator {

    public static boolean isShortCastlingCorrect(Chessboard chessboard, Figure testedFigure) {

        return false;
    }

    public static boolean isLongCastlingCorrect(Chessboard chessboard, Figure testedFigure) {

        return false;
    }

    public static boolean isEnPassantCorrect(Chessboard chessboard, Figure testedFigure, Move lastMove) {

        return false;
    }
}
