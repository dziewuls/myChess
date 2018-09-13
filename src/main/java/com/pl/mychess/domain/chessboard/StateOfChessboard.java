package com.pl.mychess.domain.chessboard;

import com.pl.mychess.domain.model.chessboard.Chessboard;
import com.pl.mychess.domain.model.chessboard.Move;

import java.util.ArrayList;
import java.util.List;

public class StateOfChessboard {
    private List<Chessboard> historyOfArrangement;
    private List<Move> historyOfMoves;

    public StateOfChessboard() {
        this.historyOfArrangement = new ArrayList<>();
        this.historyOfMoves = new ArrayList<>();
    }

    public void addNewMove(Move move) {
        historyOfMoves.add(move);
    }

    public void addNewArrangement(Chessboard chessboard) {
        historyOfArrangement.add(chessboard);
    }

    public void backMove() {
        historyOfMoves.remove(historyOfMoves.size() - 1);
        historyOfArrangement.remove(historyOfArrangement.size() - 1);
    }

    public Move.MoveBuilder getMoveBuilder() {
        return Move.getMoveBuilder();
    }

    public List<Chessboard> getHistoryOfArrangement() {
        return historyOfArrangement;
    }

    public List<Move> getHistoryOfMoves() {
        return historyOfMoves;
    }

    public Move getLastMove() {
        return historyOfMoves.get(historyOfMoves.size() - 1);
    }

    public Chessboard getLastArragnement() {
        return historyOfArrangement.get(historyOfArrangement.size() - 1);
    }
}
