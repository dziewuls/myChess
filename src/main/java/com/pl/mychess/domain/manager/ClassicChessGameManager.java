package com.pl.mychess.domain.manager;

import com.pl.mychess.domain.model.state.StateOfMatch;
import com.pl.mychess.domain.port.GameManager;

import java.util.List;

public class ClassicChessGameManager implements GameManager {
    @Override
    public List<String> getTheCorrectPlaces(String chosenPlace) {
        //TODO exception kiedy poda złe pole
        return null;
    }

    @Override
    public StateOfMatch makeMove(String chosenPlace) {
        //TODO exception kiedy poda złe pole

        return null;
    }

    @Override
    public void backMove() {

    }

    @Override
    public List<String> getMovesHistory() {
        return null;
    }
}
