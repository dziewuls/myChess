package com.pl.mychess.domain.port;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.ColorOfFigure;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.match.StateOfMatch;
import com.pl.mychess.domain.model.state.Move;

import java.util.List;

public interface GameValidator {
    StateOfMatch getTheGameResult(Chessboard chessboard, ColorOfFigure currentColor);
    List<Place> getCorrectPlacesForFigure(Chessboard chessboard, Figure testedFigure, Move lastMove);
}
