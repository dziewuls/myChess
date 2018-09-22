package com.pl.mychess.domain.logic;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.ColorOfFigure;

public class GameResultValidator {
    public static boolean isCheck(Chessboard chessboard, ColorOfFigure curentColor) {

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
