package com.pl.mychess.domain.port;

import com.pl.mychess.domain.model.state.StateOfMatch;

import java.util.List;

public interface GameManager {
    List<String> getTheCorrectPlaces(String chosenPlace);
    StateOfMatch makeMove(String chosenPlace);
    void backMove();
    List<String> getMovesHistory();
}
