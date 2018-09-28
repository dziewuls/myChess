package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.ColorOfFigure;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;

class FigureFactory {
    private FigureFactory() {
    }

    static Figure createFigureByStartedPosition(char coordinateX, int coordinateY) {
        if (coordinateY == 2)
            return new Figure(TypeOfFigure.PAWN, ColorOfFigure.WHITE);
        else if (coordinateY == 7)
            return new Figure(TypeOfFigure.PAWN, ColorOfFigure.BLACK);
        else if(coordinateY == 1)
            return createFigureByX(coordinateX, ColorOfFigure.WHITE);
        else if(coordinateY == 8)
            return createFigureByX(coordinateX, ColorOfFigure.BLACK);
        else
            return null;
    }

    private static Figure createFigureByX(char coordinateX, ColorOfFigure colorOfFigure) {
        if (coordinateX == 'a' || coordinateX == 'h')
            return new Figure(TypeOfFigure.ROOK, colorOfFigure);
        else if (coordinateX == 'b' || coordinateX == 'g')
            return new Figure(TypeOfFigure.KNIGHT, colorOfFigure);
        else if (coordinateX == 'c' || coordinateX == 'f')
            return new Figure(TypeOfFigure.BISHOP, colorOfFigure);
        else if (coordinateX == 'd')
            return new Figure(TypeOfFigure.QUEEN, colorOfFigure);
        else if (coordinateX == 'e')
            return new Figure(TypeOfFigure.KING, colorOfFigure);
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
