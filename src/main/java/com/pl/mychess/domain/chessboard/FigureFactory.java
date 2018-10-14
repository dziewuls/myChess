package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.Color;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;

class FigureFactory {
    private FigureFactory() {
    }

    static Figure createFigureByStartedPosition(char coordinateX, int coordinateY) {
        if (coordinateY == 2)
            return new Figure(TypeOfFigure.PAWN, Color.WHITE);
        else if (coordinateY == 7)
            return new Figure(TypeOfFigure.PAWN, Color.BLACK);
        else if(coordinateY == 1)
            return createFigureByX(coordinateX, Color.WHITE);
        else if(coordinateY == 8)
            return createFigureByX(coordinateX, Color.BLACK);
        else
            return null;
    }

    private static Figure createFigureByX(char coordinateX, Color color) {
        if (coordinateX == 'a' || coordinateX == 'h')
            return new Figure(TypeOfFigure.ROOK, color);
        else if (coordinateX == 'b' || coordinateX == 'g')
            return new Figure(TypeOfFigure.KNIGHT, color);
        else if (coordinateX == 'c' || coordinateX == 'f')
            return new Figure(TypeOfFigure.BISHOP, color);
        else if (coordinateX == 'd')
            return new Figure(TypeOfFigure.QUEEN, color);
        else if (coordinateX == 'e')
            return new Figure(TypeOfFigure.KING, color);
        else
            return null;
    }

    static Figure createCopyOfFigure(Figure figureForCopy) {
        if (figureForCopy == null)
            return null;
        else
            return new Figure(figureForCopy);
    }
}
