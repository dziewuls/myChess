package com.pl.mychess.domain.port;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.chessboard.TypeOfFigure;
import com.pl.mychess.domain.model.state.MatchResult;
import com.pl.mychess.domain.model.state.TypeOfCustomMove;

import java.util.List;
import java.util.Map;

public interface GameManager {
    void makeMove(Place chosenPlace, TypeOfCustomMove typeOfCustomMove, TypeOfFigure typeOfPawnTransformFigure);
    void backMove();
    Map<Place, TypeOfCustomMove> getCorrectMoveOptions(Place chosenPlace);
    List<String> getMovesHistory();
    MatchResult getGameResult();
    Chessboard getCurrentChessboard();
}
