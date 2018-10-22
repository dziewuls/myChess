package com.pl.mychess.domain.model.state;

import com.pl.mychess.domain.model.chessboard.Color;

import java.util.ArrayList;
import java.util.List;

public class MatchStatus {
    private List<Move> historyOfMoves;
    private Color currentPlayerColor;

    public MatchStatus() {
        this.currentPlayerColor = Color.WHITE;
        this.historyOfMoves = new ArrayList<>();
    }

    public void newMove(Move move) {
        historyOfMoves.add(move);
        currentPlayerColor = (currentPlayerColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public void backMove() {
        historyOfMoves.remove(historyOfMoves.size() - 1);
        currentPlayerColor = (currentPlayerColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public List<Move> getHistoryOfMoves() {
        return historyOfMoves;
    }

    public List<String> getHistoryOfMovesInString() {
        List<String> result = new ArrayList<>();

        historyOfMoves.forEach(m -> result.add(m.toString()));

        return result;
    }

    public Move getLastMove() {
        if(historyOfMoves.isEmpty()) return null;
        return historyOfMoves.get(historyOfMoves.size() - 1);
    }

}
