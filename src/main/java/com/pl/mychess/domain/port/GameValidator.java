package com.pl.mychess.domain.port;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Color;
import com.pl.mychess.domain.model.chessboard.Figure;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.Move;

import java.util.List;

public interface GameValidator {
    MatchResult getTheGameResult(Chessboard chessboard, Color currentColor);
    List<Place> getCorrectPlacesForFigure(Chessboard chessboard, Figure testedFigure, Move lastMove);
}
