package com.pl.mychess.domain.port.api;

import java.util.List;
import java.util.Map;

public interface UserInterface {
    void viewChessboard(Map<String, String> chessboard);
    void viewCorrectMoves(Map<String, String> chessboard, List<String> places);
    void viewMatchResult(String result);
    String getPlaceForMove();
    String getFigureForPawnTransform();
}
