package com.pl.mychess.domain.port;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Place;
import com.pl.mychess.domain.model.state.MatchResult;

import java.util.List;

public interface GameManager {
    void makeMove(String chosenPlace);
    void backMove();
    List<Place> getCorrectMoveOptions(String chosenPlace);
    List<String> getMovesHistory();
    MatchResult getGameResult();
    Chessboard getCurrentChessboard();
}
