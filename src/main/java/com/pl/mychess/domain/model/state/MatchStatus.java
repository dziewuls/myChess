package com.pl.mychess.domain.model.state;

import com.pl.mychess.domain.model.chessboard.Color;
import com.pl.mychess.domain.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class MatchStatus {
    private List<Move> historyOfMoves;
    private Color currentPlayerColor;
    private Player whitePlayer;
    private Player blackPlayer;

    public MatchStatus() {
        this(new Player(), new Player());
    }

    public MatchStatus(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
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

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
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
        return historyOfMoves.get(historyOfMoves.size() - 1);
    }

}
